import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Ex03 extends Applet implements ActionListener {
	private Image play_image, stop_image,cur_image;
	private AudioClip snd;
	private Button play_bt=new Button("paly");
	private Button stop_bt=new Button("stop");
	
	private Panel p=new Panel();
	private BorderLayout bl=new BorderLayout();
	private FlowLayout fl=new FlowLayout();
	
public void init(){
	snd=this.getAudioClip(this.getCodeBase(),"aaa.mp3");
	play_image=this.getImage(this.getDocumentBase(),"play_img.gif");
	stop_image=this.getImage(this.getCodeBase(), "stop_img.gif");
	cur_image=stop_image;
	this.setLayout(bl);
	p.setLayout(fl);
	p.add(play_bt);
	p.add(stop_bt);
	this.add("South",p);

	
}
public void start(){
	play_bt.addActionListener(this);
	stop_bt.addActionListener(this);
}
public void paint(Graphics g){
	g.drawImage(cur_image, 50, 50, 200,100,this);
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==play_bt){
		snd.loop();
		cur_image=play_image;
	repaint();
		
	}else if(e.getSource()==stop_bt){
		snd.stop();
		cur_image=stop_image;
		repaint();
	}
	
}
}
