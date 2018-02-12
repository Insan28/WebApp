package templater;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
public class PageGenerator
{
    private static final String HTML_DIR = "templates";
    private static PageGenerator pageGenerator;
    private static Configuration configuration;
    public static PageGenerator instance()
    {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }
    public String GetPage(String filename,Map<String,Object> data)
    {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (TemplateException ex)
        {
            ex.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator()
    {
        configuration = new Configuration();
    }
}
