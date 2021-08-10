<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>File Uploader</title>
    </head>
    <body>
        <h1>File Upload Testing</h1>
        <s:form action="FileUploadAction" enctype="multipart/form-data" method="post">
            File to upload: <input type="file" name="file"/>
            <input type="submit" value="Upload"/>
        </s:form>
    </body>
</html>