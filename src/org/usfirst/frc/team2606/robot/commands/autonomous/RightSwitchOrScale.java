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


public class RightSwitchOrScale extends CommandGroup {
    public RightSwitchOrScale(int whereToGo) {
        //Only Forward
        if(whereToGo==0) {
            addSequential(new BreakPlane());
            addSequential(new GoForward(4));
        }
        //Switch
        if(whereToGo==1) {
            addSequential(new BreakPlane());
            addParallel(new LiftToSwitchHeight());
            addSequential(new GoForward(3.5));
            addSequential(new RotateLeft(1400));
            addSequential(new SlowCubeEject());
            addParallel(new LowerLift());
            addSequential(new GoBackward(300));
        }
        //Scale
        if(whereToGo==2) {
            addSequential(new BreakPlane());
            addSequential(new GoForward(7));
            addSequential(new RotateLeft(1400));
            addParallel(new LiftToScaleHeight());
            addSequential(new BackupAndReturn());
            addSequential(new FastCubeEject());
            addSequential(new LowerLift());
        }
    }
}