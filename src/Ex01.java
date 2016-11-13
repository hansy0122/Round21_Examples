import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Ex01 extends Applet{
	private static String str="";
	
	public void init(){
		str+="init() -->";
	}
	public void start(){
		str+="start() -->";
	}
	public void paint(Graphics g){
		str+="paint() -->";
		g.drawString(str, 0, 100);
	}
	public void stop(){
		str+="stop() -->";
	}
	public void destroy(){
		
	}
}
