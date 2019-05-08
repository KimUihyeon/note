using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Media;
using System.Net.NetworkInformation;
using System.IO;
using System.Text;

namespace kuh
{
  /// <summary>
  /// Interaction logic for App.xaml
  /// </summary>
  ///
  public partial class App : Application
  {
    static public bool Authorized { get; set ;}
    public App()
    {
      /// Delegate 선언 
      this.DispatcherUnhandledException += App_DispatcherUnhandledException;
    }

    ///  WPF 전용 모든 Exception이 이 함수를 타도록함. 
    private void App_DispatcherUnhandledException(object sender, System.Windows.Threading.DispatcherUnhandledExceptionEventArgs e)
    {
      string error = "";

      var errorMessage = e.Exception; // Exception 객체
      error = GetErrorMessage(e.Exception); /// InnerException 관련 함수 

      e.Handled = true; // true  == 더 이상의 Exception Event bubbling 을 막아줌
    }


    /// InnerException 돌면서 StringBuilder에 Append 시켜줌
    private string GetErrorMessage(Exception e)
    {
      StringBuilder b = new StringBuilder();
      while (true)
      {
        b.AppendLine($"{e?.Message}   || { e?.StackTrace}" );


        if(e.InnerException!=null)
        {
          b.AppendLine($"{e?.InnerException}");
          e = e.InnerException;
        }
        else // Null
          break;
      }

      b.Append("---------------------------------");
      b.Append("관리자에게 문의해주시기 바랍니다.");
      return b.ToString();
    }


  }
}
