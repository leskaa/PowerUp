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


public class LeftSwitchOrScale extends CommandGroup {
    public LeftSwitchOrScale(int whereToGo) {
        //Only Forward
        if(whereToGo==0){
            addSequential(new BreakPlane());
            addSequential(new GoForward());
        }
        //Switch
        if(whereToGo==1) {
            addSequential(new BreakPlane());
            addParallel(new LiftToSwitchHeight());
            addSequential(new LeftToSwitch());
            addSequential(new RotateRight());
            addSequential(new SlowCubeEject());
        }
        //Scale
        if(whereToGo==2) {
            addSequential(new BreakPlane());
            addParallel(new LiftToScaleHeight());
            addSequential(new GoForward());
            addSequential(new RotateRight());
            addSequential(new SlowCubeEject());
        }
    }
}