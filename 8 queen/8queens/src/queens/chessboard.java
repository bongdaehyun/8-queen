package queens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class chessboard extends JFrame {

	JTextField text=new JTextField();
	ArrayList<int[]> arraylist=new ArrayList<int[]>();//���� �ظ� �����ϴ� ����Ʈ
	ArrayList<int[]> arylist=new ArrayList<int[]>();//Ž�������� �����ϴ� ����Ʈ
	int count=0;//���� ����
	chessboard()
	{
		setTitle("8 queens");
		setLayout(null);
		JLabel lb=new JLabel("���� ũ��");
		lb.setSize(100,40);
		lb.setLocation(200,60);
		JButton btn=new JButton("����");
		btn.setSize(100,20);
		btn.setLocation(300,100);
		JButton btn1=new JButton("Ž������");
		btn1.setSize(100,20);
		btn1.setLocation(300,130);
		JButton btn2=new JButton("����");
		btn2.setSize(100,20);
		btn2.setLocation(300,160);
		btn.addActionListener(new actionwindow());
		btn1.addActionListener(new action());
		btn2.addActionListener(new actionexit());
		text.setSize(100,20);
		text.setLocation(200,100);
		add(btn);
		add(btn1);
		add(btn2);
		add(text);
		add(lb);
		setSize(500,300);
		setVisible(true);
	}
	class action implements ActionListener{// Ž�������� ���������� �׼�
		public void actionPerformed(ActionEvent e)
		{
			JFrame f=new JFrame("Ž�� ����");
			String number=text.getText();
			int map=Integer.parseInt(number);
			int [] queen;
			queen=new int [map];//���� �����ϴ� �迭 ����
			JButton [][]btn=new JButton[map][map];//ü�� Ʋ�� ����� ��ư �迭����
			for(int i=0;i<map;i++)
			{
				for(int j=0;j<map;j++)
				{
					if((i+j)%2==0)
					{
						btn[i][j]=new JButton("");
						btn[i][j].setBackground(Color.GRAY);
						f.add(btn[i][j]);
					}
					else
					{
						btn[i][j]=new JButton("");
						btn[i][j].setBackground(Color.WHITE);
						f.add(btn[i][j]);
					}
				}
			}
			serch s=new serch(map,btn,arylist);
			btn[0][0].addKeyListener(new KeyListener() {//�͸�Ŭ������ Ű�����ʸ� ���� space�� ������ ������ ����
				public void keyPressed(KeyEvent e)
				{
					int key=e.getKeyCode();
					if(key==KeyEvent.VK_SPACE)
					{
						s.interrupt();
					}
				}
				public void keyTyped(KeyEvent e) 
				{}
				public void keyReleased(KeyEvent e) 
				{}
			});
			f.setLayout(new GridLayout(map,map));
			f.setSize(600,600);
			f.setVisible(true);
			tracking(0,map,queen);
			s.start();
		}
	}
	class actionwindow implements ActionListener{ //�ظ� �����ִ� â�� �������ִ� �׼�
		public void actionPerformed(ActionEvent e) {
			JFrame fs =new JFrame("chess");
			String number=text.getText();
			int map=Integer.parseInt(number);
			int [] queen;
			queen=new int [map];//���� �������ִ� �迭
			JButton [][]btn=new JButton[map][map];//�⺻ Ʋ�� ����ִ� ��ư �迭
			for(int i=0;i<map;i++)
			{
				for(int j=0;j<map;j++)
				{
					if((i+j)%2==0)
					{
						btn[i][j]=new JButton("");
						btn[i][j].setBackground(Color.GRAY);
						fs.add(btn[i][j]);
					}
					else
					{
						btn[i][j]=new JButton("");
						btn[i][j].setBackground(Color.WHITE);
						fs.add(btn[i][j]);
					}
				}
			}
			fs.setLayout(new GridLayout(map,map));
			fs.setSize(600,600);
			fs.setVisible(true);
		
			tracking(0,map,queen);
			print a=new print(arraylist,map,btn,count);
			a.start();
			count=0;
		}
	}
	class actionexit implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);//�ý��� ����
		}
	}
	
	public void tracking(int row,int map,int []queen)
	{
		long start = System.currentTimeMillis(); //�����ϴ� ���� ���

		if(row==map)//���� �� ��ġ������ row==�� 
		{
			count++;//�� ����
			int[] temp=new int[map];//�迭 ����
			System.arraycopy(queen, 0, temp, 0, queen.length);//�迭 ī��
			arraylist.add(temp);//�迭�� ����Ʈ�� ����
		}
		else
		{
			for(int i=0;i<map;i++)
			{
				queen[row]=i;//ù��° ������ �ֱ�
				int []ary=new int [map];//�迭�� �����ϱ� ���� �迭 ����
				System.arraycopy(queen, 0, ary, 0, queen.length);//�迭 ī��		
				if(possible(row,queen))//Ž���ϴ� ����
				{
					tracking(row+1,map,queen);//���� ������ �̵�;
				}	
				arylist.add(ary);
			}
		}
		long end = System.currentTimeMillis(); //���α׷��� ������ ���� ���
		//System.out.println( "���� �ð� : " + ( end - start )/1000.0 +"��"); //���� �ð� ��� �� ���
		
	}
	public boolean possible(int row,int [] queen)//���� �־ �Ǵ� �� �Ǵ�
	{
		for(int i=0;i<row;i++)
		{
			if(queen[row]==queen[i])//���� ���� �ִ��� �Ǵ�
			{
				return false;
			}
			else if(Math.abs(row-i)==Math.abs(queen[row]-queen[i]))//���Ⱑ -1, 1�ΰ��� �밢��
			{
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		new chessboard();
	}
}

