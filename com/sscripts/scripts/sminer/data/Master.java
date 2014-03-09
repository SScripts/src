package sminer.data;

import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;

public enum Master {


    VARROCK_TIN(Path.VAR, Rock.TIN, Areas.VARO),
    VARROCK_IRON(Path.VAR, Rock.IRON, Areas.VARO),
    VARROCK_CLAY(Path.VAR, Rock.CLAY, Areas.VARO),
    VARROCK_SILVER(Path.VAR, Rock.SILVER, Areas.VARO),
    BARBARIAN_VILLAGE_COAL(Path.BAR, Rock.COAL, Areas.BARB),
    BARBARIAN_VILLAGE_TIN(Path.BAR, Rock.TIN, Areas.BARB),
    AL_KHARID_IRON_FIRST_SPOT(Path.ALK_ONE, Rock.IRON, Areas.AHLK_ONE),
    AL_KHARID_IRON_SECOUND_SPOT(Path.ALK_TWO, Rock.IRON,Areas.AHLK_TWO),
    AL_KHARID_IRON_THIRD_SPOT(Path.ALK_THR, Rock.IRON, Areas.AHLK_THREE),
    AL_KHARID_GOLD(Path.ALK_ONE, Rock.GOLD, Areas.AHLK_ONE),
    AL_KHARID_COAL(Path.ALK_TWO, Rock.COAL, Areas.AHLK_TWO),
    AL_KHARID_MITHRIL(Path.ALK_TWO, Rock.MITHRIL, Areas.AHLK_TWO),
    AL_KHARID_SILVER(Path.ALK_TWO, Rock.SILVER, Areas.AHLK_TWO),
    AL_KHARID_ADAMANTIT(Path.ALK_THR, Rock.ADAMANT, Areas.AHLK_THREE);

    private final Path path;
    private final Rock rock;
    private final Areas areas;

    private Master(final Path path, final Rock rock, final Areas areas){
        this.path = path;
        this.rock = rock;
        this.areas = areas;
    }

    public Tile[] getPath() {
        return path.getRockPath();
    }

    public Tile[] getBankPath() {
        return path.getBankPath();
    }

    public int[] getRock() {
        return rock.getObject_ID();
    }

    public int getOre() {
        return rock.getOre_ID();
    }

    public Area getRockAreas() {
        return  areas.getRockArea();
    }
    public Area getBankAreas() {
        return areas.getBankArea();
    }

    @Override
    public String toString() {
        String name = name().toLowerCase().replace("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
