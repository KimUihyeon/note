# Ninject DI

> 사용법

1. ionKernel.dll , Ninject.dll 의존성 주입
1. App.cs OnStartup Override
1. IocConfiguration Implements 
1. IocConfiguration override 'Load'
1. Load DI Binding
1. 사용법 -> IService serivce = Ioc.Get<IService>();

```
  public partial class App : Application
  {
    protected override void OnStartup(StartupEventArgs e) // 재정의
    {
      Ioc.Initialize(new IocContainer());     // Dependency Injection Configuration
      IService serivce = Ioc.Get<IService>(); // Get Inferface Object
      
      base.OnStartup(e);
    }
  }

    // IocConfiguration 상속
  public class IocContainer : IocConfiguration      
  {
    public override void Load()   // 추상화 재정의
    {
      Bind<IService>().To<Service>().InSingletonScope();    // IService -> Service  Binding
      Bind<Page2>().ToSelf().InTransientScope();            // Page Scope (not use WPF)
    }
  }
```