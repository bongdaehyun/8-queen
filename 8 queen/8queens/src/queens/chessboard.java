package queens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class chessboard extends JFrame {

	JTextField text=new JTextField();
	ArrayList<int[]> arraylist=new ArrayList<int[]>();//퀸의 해를 저장하는 리스트
	ArrayList<int[]> arylist=new ArrayList<int[]>();//탐색과정을 저장하는 리스트
	int count=0;//해의 개수
	chessboard()
	{
		setTitle("8 queens");
		setLayout(null);
		JLabel lb=new JLabel("방의 크기");
		lb.setSize(100,40);
		lb.setLocation(200,60);
		JButton btn=new JButton("시작");
		btn.setSize(100,20);
		btn.setLocation(300,100);
		JButton btn1=new JButton("탐색과정");
		btn1.setSize(100,20);
		btn1.setLocation(300,130);
		JButton btn2=new JButton("종료");
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
	class action implements ActionListener{// 탐색과정을 눌렀을때의 액션
		public void actionPerformed(ActionEvent e)
		{
			JFrame f=new JFrame("탐색 과정");
			String number=text.getText();
			int map=Integer.parseInt(number);
			int [] queen;
			queen=new int [map];//퀸을 저장하는 배열 생성
			JButton [][]btn=new JButton[map][map];//체스 틀을 만드는 버튼 배열생성
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
			btn[0][0].addKeyListener(new KeyListener() {//익명클래스로 키리스너를 만들어서 space를 누르면 쓰레드 정지
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
	class actionwindow implements ActionListener{ //해를 보여주는 창을 생성해주는 액션
		public void actionPerformed(ActionEvent e) {
			JFrame fs =new JFrame("chess");
			String number=text.getText();
			int map=Integer.parseInt(number);
			int [] queen;
			queen=new int [map];//퀸을 저장해주는 배열
			JButton [][]btn=new JButton[map][map];//기본 틀을 잡아주는 버튼 배열
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
			System.exit(0);//시스템 종료
		}
	}
	
	public void tracking(int row,int map,int []queen)
	{
		long start = System.currentTimeMillis(); //시작하는 시점 계산

		if(row==map)//퀸을 다 배치했을때 row==행 
		{
			count++;//해 증가
			int[] temp=new int[map];//배열 생성
			System.arraycopy(queen, 0, temp, 0, queen.length);//배열 카피
			arraylist.add(temp);//배열을 리스트에 저장
		}
		else
		{
			for(int i=0;i<map;i++)
			{
				queen[row]=i;//첫번째 열부터 넣기
				int []ary=new int [map];//배열을 복사하기 위한 배열 생성
				System.arraycopy(queen, 0, ary, 0, queen.length);//배열 카피		
				if(possible(row,queen))//탐색하는 조건
				{
					tracking(row+1,map,queen);//다음 행으로 이동;
				}	
				arylist.add(ary);
			}
		}
		long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		//System.out.println( "실행 시간 : " + ( end - start )/1000.0 +"초"); //실행 시간 계산 및 출력
		
	}
	public boolean possible(int row,int [] queen)//퀸을 넣어도 되는 지 판단
	{
		for(int i=0;i<row;i++)
		{
			if(queen[row]==queen[i])//같은 열에 있는지 판단
			{
				return false;
			}
			else if(Math.abs(row-i)==Math.abs(queen[row]-queen[i]))//기울기가 -1, 1인경우는 대각선
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

