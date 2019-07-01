using Ninject;
using Ninject.Modules;
using Ninject.Syntax;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace IocKernel
{
  public static class Ioc
  {
    private static StandardKernel _kernel;

    public static T Get<T>()
    {
      return _kernel.Get<T>();
    }

    /// <summary>
    /// Create IoC Container
    /// </summary>
    /// <param name="config">IocConfiguration Implements Object</param>
    public static void Initialize(IocConfiguration config)
    {
      if (_kernel == null)
        _kernel = new StandardKernel(config);
    }
  }
  
  public abstract class IocConfiguration : NinjectModule
  {
  }
}
