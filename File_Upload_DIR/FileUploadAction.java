/**
* Internal imports placed here
*/

/**
 * Created by uy438 on 14/5/2021.
 */
public class FileUploadAction extends HttpServlet {

    //set location where file to be stored while testing
    // correct path = ""
    // filePath = "C:\\testUpload\\";

    //declare variables
    private final String UPLOAD_DIRECTORY = "C:\\testUpload\\";

    @Action("file-upload")
    public String execute() {
        return SUCCESS;
    }

    //doPost called when form is submitted.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //check for file upload request
        //isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/HTML");
        PrintWriter out = response.getWriter();

        //try parse file or catch exception
        try {
            //create fileItem and iterator
            List<FileItem> fields  = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            Iterator<FileItem> it = fields.iterator();
            //loop through file and write to UPLOAD_DIRECTORY
            for (FileItem item : fields){
                String name = new File(item.getName()).getName();
                item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
            }
            out.println("<p> File uploaded </p>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
    }
}
