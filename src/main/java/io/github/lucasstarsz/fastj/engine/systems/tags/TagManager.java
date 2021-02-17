package io.github.lucasstarsz.fastj.engine.systems.tags;

import io.github.lucasstarsz.fastj.engine.graphics.Drawable;
import io.github.lucasstarsz.fastj.engine.systems.game.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to manage tags and taggable entities for all game scenes.
 *
 * @author Andrew Dey
 * @version 0.3.2a
 */
public class TagManager {

    private static final List<String> masterTagList = new ArrayList<>();
    private static final Map<Scene, List<Drawable>> entityLists = new HashMap<>();

    /**
     * Gets the list of taggable entities at the specified {@code Scene}.
     *
     * @param scene The scene to get the list of taggable entities from.
     * @return The list of taggable entities, as a {@code List<Drawable>}.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     * @see io.github.lucasstarsz.fastj.engine.systems.tags.TaggableEntity
     * @see Drawable
     */
    public static List<Drawable> getEntityList(Scene scene) {
        return entityLists.get(scene);
    }

    /**
     * Adds the specified tag to the master list of tags.
     *
     * @param tag The tag to add.
     */
    public static void addTagToMasterList(String tag) {
        if (!masterTagList.contains(tag)) masterTagList.add(tag);
    }

    /** Removes all the tags from the master list. */
    public static void clearTags() {
        masterTagList.clear();
    }

    /**
     * Determines whether a tag is in the master list.
     *
     * @param tag The tag to check for.
     * @return The resultant boolean from checking whether the tag is in the master list.
     */
    public static boolean doesTagExist(String tag) {
        return masterTagList.contains(tag);
    }

    /**
     * Adds the specified taggable entity to the list of taggable entities for the specified scene.
     * <p>
     * The taggable entity is only added if the specified scene does not already contain the specified taggable entity.
     *
     * @param scene          The {@code Scene} which the taggable entity will be aliased with.
     * @param taggableEntity The {@code Drawable} to add.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     * @see Drawable
     */
    public static void addTaggableEntity(Scene scene, Drawable taggableEntity) {
        if (!entityLists.get(scene).contains(taggableEntity)) {
            entityLists.get(scene).add(taggableEntity);
        }
    }

    /**
     * Removes the specified taggable entity from the list of taggable entities for the specified scene.
     *
     * @param scene          The {@code Scene} that the taggable entity is aliased with.
     * @param taggableEntity The {@code Drawable} to remove.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     * @see Drawable
     */
    public static void removeTaggableEntity(Scene scene, Drawable taggableEntity) {
        entityLists.get(scene).remove(taggableEntity);
    }

    /**
     * Adds the specified {@code Scene} as an alias to store a list of taggable entities for.
     * <p>
     * The specified {@code Scene} is only added if it is not already in the tag manager.
     *
     * @param scene The scene to add.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     */
    public static void addTaggableEntityList(Scene scene) {
        if (!entityLists.containsKey(scene)) {
            entityLists.put(scene, new ArrayList<>());
        }
    }

    /**
     * Removes the list of taggable entities aliased to the specified {@code Scene}.
     *
     * @param scene The scene to remove.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     */
    public static void removeTaggableEntityList(Scene scene) {
        entityLists.remove(scene);
    }

    /**
     * Gets all taggable entities in the specified {@code Scene} with the specified tag.
     *
     * @param scene The scene to search through.
     * @param tag   The tag to search for.
     * @return A list of taggable entities that have the specified tag.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     */
    public static List<Drawable> getAllInListWithTag(Scene scene, String tag) {
        return entityLists.get(scene).stream()
                .filter(obj -> obj.hasTag(tag))
                .collect(Collectors.toList());
    }

    /**
     * Gets all taggable entities from all {@code Scene}s with the specified tag.
     *
     * @param tag The tag to search for.
     * @return A list of taggable entities that have the specified tag.
     * @see Drawable
     */
    public static List<Drawable> getAllWithTag(String tag) {
        return entityLists.values().parallelStream()
                .flatMap(List::parallelStream)
                .filter(obj -> obj.hasTag(tag))
                .collect(Collectors.toList());
    }

    /**
     * Clears the taggable entity list aliased to the specified scene.
     *
     * @param scene The scene to clear the list of taggable entities for.
     * @see io.github.lucasstarsz.fastj.engine.systems.game.Scene
     */
    public static void clearEntityList(Scene scene) {
        entityLists.get(scene).clear();
    }

    /** Wipes the {@code TagManager} of all aliases and tags. */
    public static void reset() {
        for (List<Drawable> entityList : entityLists.values()) entityList.clear();
        entityLists.clear();
        clearTags();
    }
}
