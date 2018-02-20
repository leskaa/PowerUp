package org.usfirst.frc.team2606.robot.commands.autonomous;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class GoForward extends PathfinderCommand{
    public GoForward() {
        super(new Waypoint[]{
                new Waypoint(0, 0, 0),
                new Waypoint(7, 0, Pathfinder.d2r(5))
        });
    }
}
