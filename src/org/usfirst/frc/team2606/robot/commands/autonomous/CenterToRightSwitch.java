package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2606.robot.Robot;
import org.usfirst.frc.team2606.robot.RobotMap;


public class CenterToRightSwitch extends PathfinderCommand {
    public CenterToRightSwitch() {
        super(new Waypoint[]{
                new Waypoint(0, 0, 0),
                new Waypoint(1.7, -1, 0)
        });
    }
}