package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2606.robot.Robot;
import org.usfirst.frc.team2606.robot.RobotMap;


public class CenterSwitchPlace extends CommandGroup {
    public CenterSwitchPlace(int side) {
        if(side==0){
            addSequential(new BreakPlane());
            addSequential(new CenterToLeftSwitch());
            addParallel(new LiftToSwitchHeight());
            addSequential(new SlowCubeEject());
        }
        if(side==1) {
            addSequential(new BreakPlane());
            addSequential(new CenterToRightSwitch());
            addParallel(new LiftToSwitchHeight());
            addSequential(new SlowCubeEject());
        }
    }
}