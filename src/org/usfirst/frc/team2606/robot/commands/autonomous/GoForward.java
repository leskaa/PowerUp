package org.usfirst.frc.team2606.robot.commands.autonomous;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class GoForward extends PathfinderCommand{
    public GoForward(double distance) {
        super(new Waypoint[]{
                new Waypoint(0, 0, 0),
                new Waypoint(distance, 0, Pathfinder.d2r(5))
        });
    }
}
