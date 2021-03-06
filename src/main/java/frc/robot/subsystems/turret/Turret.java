/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Turret extends SubsystemBase {

  //9 is turr
  private WPI_TalonSRX turret = new WPI_TalonSRX(9);
  private double percentage = .10;
  private int mode;
  /**
   * Creates a new Turret.
   */
  public Turret() {
    //turret.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    //turret.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
  }

  public void setMotor(ControlMode controlMode, double percentage){
   // turret.set(controlMode, percentage);
  }

  public double turretAutoAim(){
   // Robot.visionMode.setCameraMode(3);
   /*
    try{
      if(Robot.pixyCam.getBlockX(0) > 145 && Robot.pixyCam.getBlockX(0) < 155){
        SmartDashboard.putString("SHOOTER", "LOCK");
        return 0;
      } else{
        if(Robot.pixyCam.getBlockX(0) > 155){
          SmartDashboard.putString("SHOOTER", "NOT LOCKED");
          return this.percentage;
        } else if(Robot.pixyCam.getBlockX(0) < 145){
          SmartDashboard.putString("SHOOTER", "NOT LOCKED");
          return - this.percentage;
        }
      }
    } catch(Exception ex){
      SmartDashboard.putString("SHOOTER", "NOT LOCKED");
      return .10;
    }
    */
    return 0;
  }

  public void setTurretMode(int mode){
    this.mode = mode;
  }
  

  @Override
  public void periodic() {
    setMotor(ControlMode.PercentOutput, Robot.oi.operatorController.leftStick.getX() * .2);
    //this.setMotor(ControlMode.PercentOutput, Robot.oi.operatorController.rightStick.getX() * .30);
    //if(mode == 1){
    //  this.setMotor(ControlMode.PercentOutput, turretAutoAim());
   // } else{
   /// }
    /*
    if(mode == 0){
      if(turret.isFwdLimitSwitchClosed() == 1){
      } else{
        this.setMotor(ControlMode.PercentOutput, - Robot.oi.operatorController.rightStick.getX() * .2);
      }
    } else if(mode == 1){
      if(turret.isFwdLimitSwitchClosed() == 1){
        this.setMotor(ControlMode.PercentOutput, turretAutoAim());
      } else{
        this.setMotor(ControlMode.PercentOutput, - turretAutoAim());
      }
    }
    */
  }
}
