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
						btn[j][k].setText("Queen");//���� ������ �ʴ� �ص鿡�� queen�̶�� ���ڸ� �ο�
					}
					Thread.sleep(500);
					for(int h=0;h<map;h++)
					{
						for(int j=0;j<map;j++)
						btn[h][j].setText("");//�� ���� �ظ� �����ֱ� ���� ���� �����
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list.clear();
			break;
		}
		JFrame fr=new JFrame("���â");
		JLabel lab=new JLabel("���� ����"+count);
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