package s2_week8;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * 
 * @author Brian Dyck
 * Based on exercise 14.9b Intro to Java Programming, Comprehensive Version (10th Edition),  Y. Daniel Liang
 * demonstrates javafx circles, arcs, groups, Platform.runLater()
 * 
 * 100 multicolor spinning fans
 *
 */
public class Exercise14_9 extends Application 
{
	ArrayList<Fan> alFans = new ArrayList<>();
	
	private class Fan extends Group
	{
		private int size;
		private Color color;
		
		public Fan(int size, Color color)
		{
			this.size = size;
			this.color = color;
			
			Circle obCirFan = new Circle(size);
			obCirFan.setFill(new Color(0,0,0,0) );
			obCirFan.setStroke(color);
			
			this.getChildren().add(obCirFan);
			
			for(int i = 0; i < 4; i++)
			{
				Arc obFanBlade = new Arc(0, 0, size*0.9, size*0.9, 45+i*90, 45);
				obFanBlade.setFill(color);
				obFanBlade.setType(ArcType.ROUND);	
				this.getChildren().add(obFanBlade);
			}
		}
	}

	@Override
	public void start(Stage obStage) throws Exception 
	{
		GridPane obPane = new GridPane();
		for(int i = 0; i <10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				Fan obFan = new Fan(10, randomColor());
				this.alFans.add(obFan);
				obPane.add(obFan, j, i);
			}
		}
		
		
		Scene obScene = new Scene(obPane, 800, 600);
		obStage.setScene(obScene);
		obStage.setTitle("Fans");
		obStage.show();
	
		startTask();
	}
	
	public void startTask()
	{
		Thread obThread = new Thread(() -> runTask());
		
		obThread.setDaemon(true);
		obThread.start();
	}
	
	

	private void runTask()
	{
		try
		{
						
			while (true)
			{
				Platform.runLater(() ->	{
					for(Fan curFan : this.alFans)
						{
							curFan.setRotate(curFan.getRotate() + 10);
						};
				});
				
				Thread.sleep(25);
				
			
			}
			

		}
		catch(InterruptedException exp)
		{
			exp.printStackTrace();
		}
	}

	public static void main(String[] args) 
	{
		Application.launch(args);

	}

	private Color randomColor()
	{
		return new Color(Math.random(), Math.random(), Math.random(), 1);
	}
}
