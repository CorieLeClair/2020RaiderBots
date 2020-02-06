/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;

public class PixyCam extends SubsystemBase {
  public static SPI spi = new SPI(Port.kOnboardCS0);
  public Pixy2 pixycam = Pixy2.createInstance(LinkType.SPI);
  boolean isCamera = false;
  //private SPILink spi;
  int state=-1;
  // bob drive helped, handleDeadband; - dead band control
  
  public PixyCam() {

  }

  public double getBlockX(int index){
    try{
      ArrayList<Block> blocks = pixycam.getCCC().getBlocks(); //assign the data to an ArrayList for convinience
      return blocks.get(index).getX();
    } catch(Exception exception){
      System.out.println("failed with exception");
      return 0;
    }
  }

  public double getBlockY(int index){
    try{
      ArrayList<Block> blocks = pixycam.getCCC().getBlocks(); //assign the data to an ArrayList for convinience
      return blocks.get(index).getY();
    } catch(Exception exception){
      System.out.println("failed with exception");
      return 0;
    }
  }

  @Override
  public void periodic() {
    if(!isCamera)
      state = pixycam.init(1); // if no camera present, try to initialize
    isCamera = state>=0;
    
    SmartDashboard.putBoolean("Camera", isCamera);   //publish if we are connected
    pixycam.getCCC().getBlocks(false,255,255); //run getBlocks with arguments to have the camera
                                               //acquire target data
    ArrayList<Block> blocks = pixycam.getCCC().getBlocks(); //assign the data to an ArrayList for convinience
    if(blocks.size() > 0)
    {
      double xcoord = blocks.get(0).getX();       // x position of the largest target
      double ycoord = blocks.get(0).getY();       // y position of the largest target
      String data   = blocks.get(0).toString();   // string containing target info
      SmartDashboard.putBoolean("present", true); // show there is a target present
      SmartDashboard.putNumber("Xccord",xcoord);
      SmartDashboard.putNumber("Ycoord", ycoord);
      SmartDashboard.putString("Data", data );
      SmartDashboard.putNumber("a", blocks.get(0).getHeight());      
    }
    else
      SmartDashboard.putBoolean("present", false);
      SmartDashboard.putNumber("blocks detected", blocks.size()); //push to dashboard how many targets are detected
  }
}