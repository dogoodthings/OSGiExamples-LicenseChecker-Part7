# SAP ECTR OSGi Example - license checker
How to install an extra license checker to forbid the usage of SAP ECTR professional license to certain users
Setup project in eclipse:

1. File - Import - Maven - Checkout Maven Projects from SCM
2. SCM URL: git, https://github.com/dogoodthings/OSGiExamples-LicenseChecker-Part7.git
3. wait a while :P
4. open pom.xml and check the property ectr.installation.directory
5. locate your ECTR installation ( in most cases it is C:\Program Files (x86)\SAP\ECTR )
6. edit pom.xml and set the property ectr.installation.directory to this path
7. (do any meaningful changes to the source code)
8. right click on the project - run as - maven - goals: clean package - hit "run"
9. take the generated jar from target folder: OSGi-Examples-LicenseChecker-Part7-1.0.0.jar
10. goto ECTR installation, create a folder(s) OSGi-Examples\basis\plugins inside <ectr_inst_dir>\addons (mkdir "C:\Program Files (x86)\SAP\ECTR\addons\OSGi-Examples\basis\plugins")
11. put OSGi-Examples-LicenseChecker-Part7-1.0.0.jar inside OSGi-Examples\basis\plugins
12. start ECTR 
13. login to some SAP System using SAP User "IAMNOTAPRO"
14. try to open some CAD Assembly in a CAD System (beside of autocad and office, thy don't need a professional license)
    (you should get an error message because the OSGi Service forbids to use this license to the user IAMNOTAPRO=
15. note - you need at least ECTR version 5.1.13.0 to get the example code to work
