package queens;

import java.util.ArrayList;

import javax.swing.*;


class print extends Thread
{
	ArrayList <int []>list;
	int map;
	JButton [][]btn;
	int count;
	print(ArrayList <int []>list,int map,JButton [][]btn,int count)
	{
		this.map=map;
		this.list=list;
		this.btn=btn;
		this.count=count;
		
	}
	public void run()
	{
		while(true)
		{
			try {
				for(int i=0;i<list.size();i++)
				{
					for(int j=0;j<list.get(i).length;j++)
					{
						int k=list.get(i)[j];
						btn[j][k].setText("Queen");//퀸이 만나지 않는 해들에게 queen이라는 글자를 부여
					}
					Thread.sleep(500);
					for(int h=0;h<map;h++)
					{
						for(int j=0;j<map;j++)
						btn[h][j].setText("");//그 다음 해를 보여주기 위한 글자 지우기
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.clear();
			break;
		}
		JFrame fr=new JFrame("결과창");
		JLabel lab=new JLabel("해의 개수"+count);
		fr.setLayout(null);
		fr.setVisible(true);
		fr.setSize(300,300);
		lab.setBounds(000, 000, 100, 100);
		fr.add(lab);
		
	}
}
class serch extends Thread
{
	int map;
	JButton [][]btn;
	ArrayList <int []>lis;
	serch(int map,JButton [][]btn,ArrayList <int []>lis)
	{
		this.map=map;
		this.btn=btn;
		this.lis=lis;
	}
	public void run()
	{
		while(true)
		{
			try {
				for(int i=0;i<lis.size();i++)
				{
					for(int j=0;j<lis.get(i).length;j++)
					{
						int k=lis.get(i)[j];
						btn[j][k].setText("Queen");
					}
					Thread.sleep(100);
					for(int h=0;h<map;h++)
					{
						for(int j=0;j<map;j++)
						btn[h][j].setText("");
					}
				}
			} catch (InterruptedException e) {
				this.interrupt();
				
			}
			lis.clear();
			break;
		}
		
	}
}