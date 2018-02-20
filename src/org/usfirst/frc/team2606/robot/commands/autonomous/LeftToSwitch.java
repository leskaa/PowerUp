package org.usfirst.frc.team2606.robot.commands.autonomous;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class LeftToSwitch extends PathfinderCommand {
    public LeftToSwitch() {
        super(new Waypoint[]{
                new Waypoint(0, 0, 0),
                new Waypoint(3.5, 0.2, 0)
        });
    }
}
