import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ITreturns extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ITreturns() {
        //super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String salary = request.getParameter("salary");
        String tax = request.getParameter("deduction");
        PrintWriter pw=response.getWriter();
       // String data = "Name: " + name + ", Gender: " + gender + ", Salary: " + salary + ", Tax: " + tax + "\n";
        try {
        File file = new File("/Users/kritika/Desktop/tax.txt");
        file.createNewFile();
        FileOutputStream f2=new FileOutputStream(file);
        pw.println("\t"+name+"\t"+gender+"\t");
        pw.println("\t"+salary+"\t"+tax);
        f2.write(("hello "+name+" for your salary "+salary+"your tax is "+tax).getBytes());
        f2.close();
        response.getWriter().append("Served at:").append(request.getContextPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

