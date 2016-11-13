import java.applet.*;
import java.awt.*;
import java.awt.event.*;



class Horse extends Canvas implements Runnable{
	private Image ho_run,ho_stop,ho_cur;
	private int num;
	private int xpos=5;
	public static int rank=1;
	public Horse(Image ho_run, Image ho_stop,int num){
		this.ho_run=ho_run;
		this.ho_stop=ho_stop;
		this.num=num;
		this.ho_cur=ho_stop;
	}
	public void paint(Graphics g){
		Dimension di=this.getSize(); // 말 한마리의 크기
		g.drawImage(ho_cur, xpos, 10, 20, (int)(di.getHeight()*0.9),this);
		g.drawLine(5,(int)(di.getHeight()*0.9),(int)(di.getWidth()*0.95), (int)(di.getHeight()*0.9));
	}
	public void run(){
		ho_cur=ho_run;
		Dimension di=this.getSize();
		for(int i=5;i<=di.getWidth()-25;i+=(Math.random()*3)+2){
			xpos=i;
			this.repaint();
			try{
				Thread.sleep((int)(Math.random()*300+50));
			}catch(InterruptedException e ){
			}
			ho_cur=ho_stop;
			this.repaint();
			Ex04.rank_ta.append(rank+++"등 말 번호"+num+"\n");
		}
	}
	
}



public class Ex04 extends Applet implements ActionListener{
	private Label ho_num_lb=new Label("말의 수: ",Label.RIGHT);
	private TextField ho_num_tf=new TextField(5);
	private Button set_bt=new Button("설정");
	private Button reset_bt=new Button("초기화");
	private Panel p=new Panel();
	private GridBagLayout gbl=new GridBagLayout();
	
	
	
	private Panel sta_p=new Panel();
	private Button start_bt=new Button("출발");
	public static TextArea rank_ta=new TextArea(5,20);
	private Panel p1=new Panel();
	private BorderLayout bl1=new BorderLayout();
	private Panel p2=new Panel();
	private FlowLayout fl=new FlowLayout();
	
	private BorderLayout bl=new BorderLayout();

	private Horse[] ho;
	
	
public void init(){
	this.setLayout(bl);
	p.setLayout(gbl);
	p.add(ho_num_lb);
	p.add(ho_num_tf);
	p.add(set_bt);
	p.add(reset_bt);
	this.add("North",p);
	
	this.add("Center", sta_p);
	p1.setLayout(bl1);
	p2.setLayout(fl);
	p2.add(start_bt);
	p1.add("North", p2);
	p1.add("Center", rank_ta);
	this.add("South", p1);
	
}
public void start(){
	ho_num_tf.addActionListener(this);
	set_bt.addActionListener(this);
	start_bt.addActionListener(this);
	reset_bt.addActionListener(this);
}
public void paint(Graphics g){
	
}
public void stop(){

}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==ho_num_tf ||e.getSource()==set_bt){
		String c=ho_num_tf.getText();
		if(c==null||c.trim().length()==0){return;}
		int num=Integer.parseInt(c.trim());
		String a=this.getParameter("ho_run");
		String b=this.getParameter("ho_stop");
		Image aa=this.getImage(this.getCodeBase(),a);
		Image bb=this.getImage(this.getCodeBase(),b);
		this.remove(sta_p);
		sta_p=new Panel();
		sta_p.setLayout(new GridLayout(num,1));
		
		ho=new Horse[num];
		for(int i=0;i<num;i++){
			ho[i]=new Horse(aa,bb,i+1);
			sta_p.add(ho[i]);
		}
		this.add("Center",sta_p);
		this.validate(); //변경된거 적용후 paint 호출
	}
	else if(e.getSource()==start_bt){
		for(int i=0;i<ho.length;i++){
			new Thread(ho[i]).start();
		}
	}
	else if(e.getSource()==reset_bt){
		rank_ta.setText("");
		ho_num_tf.setText("");
		this.remove(sta_p);
		sta_p=new Panel();
		this.add("Center", sta_p);
		this.validate();
		Horse.rank=1;
		ho_num_tf.requestFocus();
		
	}
}
}
