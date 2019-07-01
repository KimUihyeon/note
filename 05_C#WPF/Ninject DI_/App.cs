using IocKernel;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Windows;
using WpfApp1.Serivce;
using WpfApp1.Views;

namespace WpfApp1
{
  public partial class App : Application
  {
    protected override void OnStartup(StartupEventArgs e)
    {
      Ioc.Initialize(new IocContainer());     // Dependency Injection Configuration
      IService serivce = Ioc.Get<IService>(); // Get Inferface Object
      
      base.OnStartup(e);
    }
  }

    // IocConfiguration 상속
  public class IocContainer : IocConfiguration
  {
    public override void Load()  
    {
      Bind<IService>().To<Service>().InSingletonScope();    // IService -> Service  Binding
      Bind<Page2>().ToSelf().InTransientScope();            // Page Scope (not use WPF)
    }
  }
}