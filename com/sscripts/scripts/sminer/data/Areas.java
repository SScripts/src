package sminer.data;

import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;


public enum Areas {

    AHLK_ONE(new Area(
            new Tile(3291, 3285, 0),
            new Tile(3304, 3283, 0),
            new Tile(3305, 3290, 0),
            new Tile(3297, 3291, 0)

    ), new Area(
            new Tile(3267, 3175, 0),
            new Tile(3272, 3175, 0),
            new Tile(3272, 3165, 0),
            new Tile(3267, 3165, 0)
    )),
    AHLK_TWO(new Area(
            new Tile(3304,3297, 0),
            new Tile(3304,3306, 0),
            new Tile(3293,3306, 0),
            new Tile(3292,3298, 0)
    ),
            new Area(
            new Tile(3267, 3175, 0),
            new Tile(3272, 3175, 0),
            new Tile(3272, 3165, 0),
            new Tile(3267, 3165, 0)
    )),
    AHLK_THREE(new Area(
            new Tile(3304,3308, 0),
            new Tile(3295,3308, 0),
            new Tile(3294,3317, 0),
            new Tile(3304,3317, 0)
    ),
            new Area(
            new Tile(3267, 3175, 0),
            new Tile(3272, 3175, 0),
            new Tile(3272, 3165, 0),
            new Tile(3267, 3165, 0)
    )),
    VARO(new Area(
            new Tile(3181, 3380, 0),
            new Tile(3165, 3364, 0),
            new Tile(3178, 3357, 0),
            new Tile(3195, 3371, 0)

    ),new Area(
            new Tile(3178, 3446, 0),
            new Tile(3178, 3431, 0),
            new Tile(3195, 3430, 0),
            new Tile(3196, 3448, 0)
    )),
    BARB(new Area(
            new Tile(3076, 3416, 0),
            new Tile(3088, 3415, 0),
            new Tile(3085, 3426, 0),
            new Tile(3077, 3426, 0)
    ), new Area(
            new Tile(3091, 3501, 0),
            new Tile(3091, 3489, 0),
            new Tile(3101, 3489, 0),
            new Tile(3101, 3500, 0)
    ));

    private final Area rockArea;
    private final Area bankArea;

    private Areas (Area rockArea, Area bankArea){
        this.rockArea = rockArea;
        this.bankArea = bankArea;
    }

    public Area getRockArea() {
        return rockArea;
    }

    public Area getBankArea() {
        return  bankArea;
    }

}