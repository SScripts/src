package sminer.data;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;


public enum Master {


    VARROCK_TIN(Path.VAR, Rock.TIN, Field.VARO),
    VARROCK_IRON(Path.VAR, Rock.IRON, Field.VARO),
    VARROCK_CLAY(Path.VAR, Rock.CLAY, Field.VARO),
    VARROCK_SILVER(Path.VAR, Rock.SILVER, Field.VARO),
    BARBARIAN_VILLAGE_COAL(Path.BAR, Rock.COAL, Field.BARB),
    BARBARIAN_VILLAGE_TIN(Path.BAR, Rock.TIN, Field.BARB),
    AL_KHARID_IRON_FIRST_SPOT(Path.ALK_ONE, Rock.IRON, Field.AHLK_ONE),
    AL_KHARID_IRON_SECOUND_SPOT(Path.ALK_TWO, Rock.IRON,Field.AHLK_TWO),
    AL_KHARID_IRON_THIRD_SPOT(Path.ALK_THR, Rock.IRON, Field.AHLK_THREE),
    AL_KHARID_GOLD(Path.ALK_ONE, Rock.GOLD, Field.AHLK_ONE),
    AL_KHARID_COAL(Path.ALK_TWO, Rock.COAL, Field.AHLK_TWO),
    AL_KHARID_MITHRIL(Path.ALK_TWO, Rock.MITHRIL, Field.AHLK_TWO),
    AL_KHARID_SILVER(Path.ALK_TWO, Rock.SILVER, Field.AHLK_TWO),
    AL_KHARID_ADAMANTIT(Path.ALK_THR, Rock.ADAMANT, Field.AHLK_THREE);

    private final Path path;
    private final Rock rock;
    private final Field area;

    private Master(final Path path, final Rock rock, final Field area){
        this.path = path;
        this.rock = rock;
        this.area = area;
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
        return  area.getRockArea();
    }
    public Area getBankAreas() {
        return area.getBankArea();
    }

    @Override
    public String toString() {
        String name = name().toLowerCase().replace("_", " ");
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
