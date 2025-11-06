package br.com.rpires.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Service for validating female names against a known set of female names.
 * This service is case-insensitive, thread-safe, and handles whitespace in names.
 * 
 * @since 21
 */
public class NameService {
    private final Set<String> knownFemaleNames;
    private final ConcurrentHashMap<String, Boolean> nameCache;

    /**
     * Creates a NameService with a default set of common female names.
     */
    public NameService() {
        this(Set.of(
            "maria", "ana", "joana", "patricia", "marina",
            "carla", "laura", "lucia", "beatriz", "juliana"
        ));
    }

    /**
     * Creates a NameService with a custom set of female names.
     *
     * @param femaleNames Set of female names to use for validation (case-insensitive)
     * @throws IllegalArgumentException if femaleNames is null or empty
     */
    public NameService(Set<String> femaleNames) {
        validateNameSet(femaleNames);
        this.knownFemaleNames = femaleNames.stream()
            .filter(Objects::nonNull)
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toUnmodifiableSet());
        this.nameCache = new ConcurrentHashMap<>();
    }

    /**
     * Checks if all names in the provided list are known female names.
     * This implementation is thread-safe and uses caching for better performance
     * with repeated lookups.
     *
     * @param names List of names to check
     * @return true if all names are female names, false otherwise
     * @throws IllegalArgumentException if the names list is null or empty
     */
    public boolean allAreFemale(List<String> names) {
        validateNameList(names);
        
        return names.parallelStream()
            .filter(Objects::nonNull)
            .map(name -> nameCache.computeIfAbsent(
                name.trim().toLowerCase(),
                k -> knownFemaleNames.contains(k)
            ))
            .allMatch(Boolean::booleanValue);
    }

    /**
     * Gets an unmodifiable view of the known female names.
     *
     * @return Set of known female names (lowercase)
     */
    public Set<String> getKnownFemaleNames() {
        return Collections.unmodifiableSet(knownFemaleNames);
    }

    /**
     * Clears the internal name validation cache.
     * This can be useful in long-running applications to prevent memory growth
     * if many unique invalid names are checked.
     */
    public void clearCache() {
        nameCache.clear();
    }

    private void validateNameSet(Set<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("Set of female names cannot be null or empty");
        }
    }

    private void validateNameList(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("List of names to check cannot be null or empty");
        }
    }
}