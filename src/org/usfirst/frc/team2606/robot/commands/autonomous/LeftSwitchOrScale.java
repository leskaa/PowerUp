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
        if(whereToGo==0) {
            addSequential(new BreakPlane());
            addSequential(new GoForward(7));
        }
        //Switch
        if(whereToGo==1) {
            addSequential(new BreakPlane());
            addParallel(new LiftToSwitchHeight());
            addSequential(new LeftToSwitch());
            addSequential(new RotateRight(1400));
            addSequential(new SlowCubeEject());
            addParallel(new LowerLift());
            addSequential(new GoBackward(300));
        }
        //Scale
        if(whereToGo==2||whereToGo==3||whereToGo==4) {
            //addSequential(new BreakPlane());
            addSequential(new GoForward(7));
            addSequential(new RotateRight(1300));
            addParallel(new LiftToScaleHeight());
            addSequential(new BackupAndReturn());
            addSequential(new FastCubeEject());
            addParallel(new LowerLift());
            addSequential(new GoBackward(300));
        }
        //Switch After Scale
        if(whereToGo==3) {
            addSequential(new RotateRight(700));
            addParallel(new IntakeFor(4050));
            addSequential(new GoForward(2.7));
            addSequential(new RotateRight(300));
            addSequential(new GoBackward(300));
            addSequential(new LiftToSwitchHeight());
            addSequential(new SlowCubeEject());
        }
        //Far Switch After Scale
        if(whereToGo==4) {
            addSequential(new RotateRight(2750));
            addSequential(new GoForward(1.8));
            addSequential(new RotateLeft(1400));
            addSequential(new GoForward(2));
            addSequential(new RotateRight(1500));
            addSequential(new GoForward(0.3));
            addParallel(new IntakeFor(500));
            addSequential(new LiftToSwitchHeight());
            addSequential(new SlowCubeEject());
            addSequential(new GoBackward(300));
            addSequential(new LowerLift());
        }
        //Only Far Switch
        if(whereToGo ==5) {
            addSequential(new GoForward(4.5));
            addSequential(new RotateRight(1400));
            addSequential(new GoForward(2));
            addSequential(new RotateRight(1500));
            addSequential(new GoForward(0.3));
            addParallel(new IntakeFor(500));
            addSequential(new LiftToSwitchHeight());
            addSequential(new SlowCubeEject());
            addSequential(new GoBackward(300));
            addSequential(new LowerLift());
        }
    }
}