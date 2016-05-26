package sx.xss.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.annotation.SuppressLint;
import android.content.Context;

import sx.xss.diary4heart.R;

@SuppressLint("SdCardPath")
public class Copyer 
{
	private Context context;
	public Copyer(Context context)
	{
		this.context = context;
	}

	public void copyer()
	{	//数据库文件夹路径
		String db_filepath = "/data/data/sx.xss.diary4heart/databases";
		String db_name = "diary4heart.db";	//数据库文件名
		String db_path = db_filepath + "/" + db_name;	 //数据库绝对路径
		//判断db文件夹是否存在，不存在则创建
		File file = new File(db_filepath);
		if(!file.exists())
			file.mkdir();
		//判断数据库是否存在，不存在则将raw目录下的数据库写入
		if(!(new File(db_path)).exists())
		{	//要写入的数据库
			InputStream in = context.getResources().openRawResource(R.raw.diary4heart);
			try {
				FileOutputStream out = new FileOutputStream(db_path);
				byte data[] = new byte[1024];
				int index = 0;
				try{
				while((index = in.read(data)) != -1)
					out.write(data, 0, index);
				in.close();
				out.close();
				}catch(Exception e){}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
