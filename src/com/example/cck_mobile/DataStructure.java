package com.example.cck_mobile;

class TextData{
	//��һ�����ݽṹ��¼�ı��ļ��ļ�����ͼƬ�ļ��ļ���
	//����¼��Ŀ���֣���Ϊ�������б��posȡ���ݣ���ͬ��
	private String txtFilePath;	//�ı��ļ�·��
	private String picFilePath;	//ͼƬ�ļ�·��
	
	public TextData(String strTxt,String strPic){
		txtFilePath=strTxt;picFilePath=strPic;
	}
	
	public String getTxtFilePath() {
		return ConstString.PATH+txtFilePath;
	}
	public String getPicFilePath() {
		return ConstString.PATH+picFilePath;
	}
}

class VideoData{
	//��¼��Ƶ���ļ���
	private String filePath;
	
	public VideoData(String str){
		filePath=str;
	}

	public String getFilePath() {
		return ConstString.PATH+filePath;
	}
}

interface ConstString{
	String PATH="/sdcard/cck/";		//�����ļ����ļ���
}