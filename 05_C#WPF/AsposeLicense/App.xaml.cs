using Aspose.Words;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;

namespace kuh
{
  /// <summary>
  /// Interaction logic for App.xaml
  /// </summary>
  public partial class App : Application
  {
    public App()
    {
    }
    private void App_Startup(object sender, StartupEventArgs e)
    {
      SetAsposeCellLicense();
			SetPdfLicense();
			SetWordLicense();

    }

    private readonly static string  라이센스Key = ""; 

    private void SetAsposeCellLicense()
    {
      Aspose.Cells.License Lic = new Aspose.Cells.License();
      System.Text.ASCIIEncoding Encoder = new System.Text.ASCIIEncoding();
      using (System.IO.MemoryStream ms = new System.IO.MemoryStream(Encoder.GetBytes( 라이센스Key )))
      {
       Lic.SetLicense(ms);
      }
    }

    private void SetPdfLicense()
    {
      Aspose.Pdf.License Lic = new Aspose.Pdf.License();
      System.Text.ASCIIEncoding Encoder = new System.Text.ASCIIEncoding();
      using (System.IO.MemoryStream ms = new System.IO.MemoryStream(Encoder.GetBytes(  라이센스Key )))
      {
        Lic.SetLicense(ms);
      }
      
    }


    private void SetWordLicense()
    {
      Aspose.Words.License Lic = new Aspose.Words.License();
      System.Text.ASCIIEncoding Encoder = new System.Text.ASCIIEncoding();
      using (System.IO.MemoryStream ms = new System.IO.MemoryStream(Encoder.GetBytes( 라이센스Key )))
      {
        Lic.SetLicense(ms);
      }
    }
  }
}
