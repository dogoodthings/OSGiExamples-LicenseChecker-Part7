package osgi;

import com.dscsag.plm.spi.interfaces.ECTRService;
import com.dscsag.plm.spi.interfaces.commons.PlmLogonData;
import com.dscsag.plm.spi.interfaces.license.LicenseCheckResult;
import com.dscsag.plm.spi.interfaces.license.LicenseCheckResultFactory;
import com.dscsag.plm.spi.interfaces.license.LicenseChecker;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Activator to register provided services
 */
public class Activator implements BundleActivator
{

  @Override
  public void start(BundleContext context) throws Exception
  {
    ECTRService ectrService = getService(context, ECTRService.class);
    LicenseHandler service = new LicenseHandler(ectrService.getLogonData());
    context.registerService(LicenseChecker.class, service, null);
  }

  @Override
  public void stop(BundleContext context)
  {
    // empty
  }

  private <T> T getService(BundleContext context, Class<T> clazz) throws Exception
  {
    ServiceReference<T> serviceRef = context.getServiceReference(clazz);
    if (serviceRef != null)
      return context.getService(serviceRef);
    throw new Exception("Unable to find implementation for service " + clazz.getName());
  }

  private class LicenseHandler implements LicenseChecker
  {
    PlmLogonData logonData;

    LicenseHandler(PlmLogonData logonData)
    {
      this.logonData = logonData;
    }

    @Override
    public LicenseCheckResult checkLicenseAvailable(String licenseCode)
    {
      String user = logonData.getUser();
      if ("IAMNOTAPRO".equals(user))
        return LicenseCheckResultFactory.licenseIsAvailable();
      return LicenseCheckResultFactory.licenseIsNotAvailable("PRO license is not available for " + user );
    }
  }

}