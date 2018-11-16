/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6489.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	Joystick joystick = new Joystick(0);
	
	/** Negative is forward **/
	Spark leftSide;
	/** Positive is forward **/
	Spark rightSide;
	
	Timer timer;

	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		leftSide = new Spark(0);
		rightSide = new Spark(1);
		
		timer = new Timer();
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		
		timer.start();
	}

	@Override
	public void autonomousPeriodic() {
		if (timer.get() < 3.0) {
			leftSide.set(-.3);
			rightSide.set(.3);
		} else if (timer.get() < 6.0) {
			leftSide.set(.3);
			rightSide.set(-.3);
		} else {
			leftSide.set(0);
			rightSide.set(0);
		}
		
		
		/*switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;
		}*/
	}

	@Override
	public void teleopPeriodic() {
		// This makes the joystick work where the robot just goes where you point the stick
		/*leftSide.set(joystick.getX() + joystick.getY());
		rightSide.set(joystick.getX() - joystick.getY());*/
		
		// Makes forward/backward use Y axis and turning use Z axis
		leftSide.set((joystick.getZ() + joystick.getY()) * .5);
		rightSide.set((joystick.getZ() - joystick.getY()) * .5);
		
		// ATTEMPT to get speed slider to work
		/*if (joystick.getY() < 0.5) {
			leftSide.set((joystick.getThrottle() / 2) + .5);
			rightSide.set((-joystick.getThrottle() / 2) + .5);
		} else if (joystick.getY() > 0.5) {
			leftSide.set((-joystick.getThrottle() / 2) + .5);
			rightSide.set((joystick.getThrottle() / 2) + .5);
		}*/
		
		
		/*value = joystick.getZ();
		value = joystick.getThrottle();
		value = joystick.getTwist();

		boolean buttonValue;
		buttonValue = joystick.getTop();
		buttonValue = joystick.getTrigger();*/
	}
}
