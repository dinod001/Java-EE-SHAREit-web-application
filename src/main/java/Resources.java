import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@WebServlet(urlPatterns = "/shareit")
@MultipartConfig
public class Resources extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part image = req.getPart("image");

        InputStream is = image.getInputStream();
        String fileName=req.getParameter("fileName");
        String extension=req.getParameter("extension");
        Boolean result=exportedFile(is,fileName,extension);
        if(result){
            resp.getWriter().print("File shared successfully");
        }
        else{
            resp.getWriter().print("File not shared");
        }
    }
    public static Boolean exportedFile(InputStream stream,String filename,String extension) throws IOException {
        String file_path = System.getProperty("user.home") + "\\Documents\\Api\\";
        String newPath=file_path + "\\" + filename + "." + extension;
        File file = new File(file_path);
        if (!file.exists()) {
            file.mkdirs();
            try {
                OutputStream File=new FileOutputStream(newPath);
                int i=0;
                while ((i=stream.read())!=-1){
                    File.write(i);
                }
                File.flush();
                File.close();
                return true;
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                OutputStream File=new FileOutputStream(newPath);
                int i=0;
                while ((i=stream.read())!=-1){
                    File.write(i);
                }
                File.flush();
                File.close();
                return true;
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
