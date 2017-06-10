package com.opengroup.longmao.gwcommon.tools;

/**
 * Java服务器测试用探针
 *
 * @project LF-Spy
 * @version 0.1.0
 * @author chenpeng
 * @email：ceponline@yahoo.com.cn
 **/
public class LfSpy {

	boolean supportHibernate = false;

	boolean supportJNDI = false;

	boolean supportJavaxSql = false;

	boolean supportJAF = false;

	boolean supportMail = false;

	boolean supportBeanUtils = false;

	boolean supportCommonLogging = false;

	boolean supportCommonCodec = false;

	boolean supportCommonCollection = false;

	boolean supportCommonDigester = false;

	boolean supportCommonLang = false;

	boolean supportJakartaRegExp = false;

	boolean supportLucene = false;

	boolean supportDom4j = false;

	boolean supportLoonframework = false;

	boolean supportMmMysqlDriver = false;

	boolean supportComMysqlDriver = false;

	boolean supportImageProcessing = false;

	boolean supportStruts = false;

	boolean supportSpring = false;

	String serverName;

	String serverIP = "127.0.0.1";

	long startTime = System.currentTimeMillis();

	long startMemory = Runtime.getRuntime().freeMemory();

	java.util.Properties prop = System.getProperties();

	String javaVersion = prop.getProperty("java.version");

	String FS = prop.getProperty("file.separator");

	int CPUTIME = 30;

	int PERCENT = 100;

	public LfSpy() {

		try {
			Class.forName("org.springframework.context.ApplicationContext");
			supportSpring = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.loon.framework.Loon");
			supportLoonframework = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.hibernate.Transaction");
			supportHibernate = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.struts.action.ActionServlet");
			supportStruts = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("javax.naming.Name");
			supportJNDI = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("javax.sql.DataSource");
			supportJavaxSql = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("javax.activation.DataSource");
			supportJAF = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("javax.mail.Message");
			supportMail = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.beanutils.MethodUtils");
			supportBeanUtils = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.logging.LogFactory");
			supportCommonLogging = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.codec.Decoder");
			supportCommonCodec = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.collections.ArrayStack");
			supportCommonCollection = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.digester.Digester");
			supportCommonDigester = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.commons.lang.SystemUtils");
			supportCommonLang = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.regexp.RE");
			supportJakartaRegExp = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.apache.lucene.index.IndexWriter");
			supportLucene = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.dom4j.Document");
			supportDom4j = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			supportMmMysqlDriver = true;
		} catch (ClassNotFoundException ex) {
		}

		try {
			Class.forName("com.mysql.jdbc.Driver");
			supportComMysqlDriver = true;
		} catch (ClassNotFoundException ex) {
		}

		loadAddress();

		try {
			java.awt.image.BufferedImage bufferedImage = new java.awt.image.BufferedImage(10, 10,
					java.awt.image.BufferedImage.TYPE_INT_RGB);
			java.awt.Graphics2D g = bufferedImage.createGraphics();
			g.drawLine(0, 0, 10, 10);
			g.dispose();

			supportImageProcessing = true;
		} catch (Throwable ex) {
		}

	}

	public String getOSarch() {
		return prop.getProperty("os.arch");
	}

	public String getTimeZone() {
		return prop.getProperty("user.timezone");
	}

	public String getNowUser() {
		return prop.getProperty("user.name");
	}

	public String getNowUserDir() {
		return prop.getProperty("user.dir");
	}

	public String getOSName() {
		return prop.getProperty("os.name");
	}

	public String getSystemModel() {
		return prop.getProperty("sun.arch.data.model");
	}

	final private long getDiskForLinuxInfo(final String dirPath) {
		try {
			String dir = dirPath.startsWith("/") ? dirPath : "/" + dirPath;
			long space = -1;
			Process process;
			Runtime run = Runtime.getRuntime();
			String osName = System.getProperty("os.name").toLowerCase();
			String command = "";
			if (osName.startsWith("linux")) {
				command = "df -k " + dir;
			}
			process = run.exec(command);
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(process.getInputStream()));
			String freeSpace = "", line;
			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					freeSpace = line;
				}
			}
			if (freeSpace == null || freeSpace.length() == 0) {
				return -1;
			}
			process.destroy();
			freeSpace = freeSpace.trim().replaceAll("\\\\", "\\/");
			String[] results = freeSpace.split(" ");
			for (int i = results.length - 1; i > 0; i--) {
				try {
					space = Long.parseLong(results[i]);
					return space;
				} catch (NumberFormatException ex) {
					continue;
				}
			}
		} catch (java.io.IOException e) {
			return -1;
		}
		return -1;
	}

	/**
	 * 获得windows下指定地址硬盘空间大小
	 * 
	 * @param dirPath
	 * @return
	 */
	final private long getDiskForWindowsInfo(String dirPath) {
		try {
			long space = -1;
			Process process;
			Runtime run = Runtime.getRuntime();
			String osName = System.getProperty("os.name").toLowerCase();
			String command = "";
			if (osName.startsWith("windows") && osName.indexOf("98") == -1) {
				command = "cmd.exe /c dir " + dirPath;
			} else if (osName.startsWith("windows") && osName.indexOf("98") != -1) {
				command = "command.com /c dir " + dirPath;
			}
			process = run.exec(command);
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(process.getInputStream()));
			String freeSpace = "", line;
			while ((line = in.readLine()) != null) {
				freeSpace = line;
			}
			if (freeSpace == null) {
				return -1;
			}
			process.destroy();
			freeSpace = freeSpace.trim();
			freeSpace = freeSpace.replaceAll("\\\\\\\\\\\\\\\\.", "");
			freeSpace = freeSpace.replaceAll(",", "");
			String[] results = freeSpace.split(" ");
			for (int i = 1; i < results.length; i++) {
				try {
					space = Long.parseLong(results[i]);
					return space;
				} catch (NumberFormatException ex) {
					continue;
				}
			}
			return space;
		} catch (java.io.IOException e) {
			return -1;
		}
	}

	public long getObjectDisk() {
		String path = prop.getProperty("user.dir");
		int index = path.indexOf(FS);
		path = path.substring(0, index);
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.startsWith("windows")) {
			return getDiskForWindowsInfo(path);
		} else if (osName.startsWith("linux")) {
			return getDiskForLinuxInfo(path);
		} else {
			return -1;
		}
	}

	public final String getMacAddress() {
		String os = System.getProperty("os.name");
		try {
			if (os.startsWith("Windows")) {
				return windowsParseMacAddress(windowsRunIpConfigCommand());
			} else if (os.startsWith("Linux")) {
				return linuxParseMacAddress(linuxRunIfConfigCommand());
			} else {
				throw new java.io.IOException("unknown operating system: " + os);
			}
		} catch (Exception ex) {
			return "Nothing";
		}
	}

	private final String linuxParseMacAddress(String ipConfigResponse) throws java.text.ParseException {
		String localHost = null;
		try {
			localHost = java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new java.text.ParseException(ex.getMessage(), 0);
		}

		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;

		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;

			if (containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}

			int macAddressPosition = line.indexOf("HWaddr");
			if (macAddressPosition <= 0)
				continue;

			String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
			if (linuxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}

		java.text.ParseException ex = new java.text.ParseException(
				"cannot read MAC address for " + localHost + " from [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final boolean linuxIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}

	private final String linuxRunIfConfigCommand() throws java.io.IOException {
		Process p = Runtime.getRuntime().exec("ifconfig");
		java.io.InputStream stdoutStream = new java.io.BufferedInputStream(p.getInputStream());

		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();

		stdoutStream.close();

		return outputText;
	}

	private final String windowsParseMacAddress(String ipConfigResponse) throws java.text.ParseException {
		String localHost = null;
		try {
			localHost = java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new java.text.ParseException(ex.getMessage(), 0);
		}

		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;

		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();

			if (line.endsWith(localHost) && lastMacAddress != null) {
				return lastMacAddress;
			}

			int macAddressPosition = line.indexOf(":");
			if (macAddressPosition <= 0)
				continue;

			String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
			if (windowsIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
//		java.text.ParseException ex = new java.text.ParseException("cannot read MAC address from [" + ipConfigResponse + "]", 0);
//		ex.printStackTrace();
//		throw ex;
		return "未知";
	}

	private final boolean windowsIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}

	private final String windowsRunIpConfigCommand() throws java.io.IOException {
		Process p = Runtime.getRuntime().exec("ipconfig /all");
		java.io.InputStream in = new java.io.BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = in.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		in.close();
		return outputText;
	}

	public double getCpuRatio() {
		double cpuRatio = 0;
		if (getOSName().toLowerCase().startsWith("windows")) {
			cpuRatio = getCpuRatioForWindows();
		} else if (getOSName().toLowerCase().startsWith("linux")) {
			cpuRatio = getCpuRatioForLinux();
		}
		return cpuRatio;
	}

	private double getCpuRatioForWindows() {
		try {
			String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,"+ "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(PERCENT * (busytime) / (busytime + idletime)).doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}
	}

	final private double getCpuRatioForLinux() {
		try {
			java.io.File file = new java.io.File("/proc/stat");
			java.io.BufferedReader br = new java.io.BufferedReader(
					new java.io.InputStreamReader(new java.io.FileInputStream(file)));
			java.util.StringTokenizer token = new java.util.StringTokenizer(br.readLine());
			token.nextToken();
			int user = Integer.parseInt(token.nextToken());
			int nice = Integer.parseInt(token.nextToken());
			int system = Integer.parseInt(token.nextToken());
			int idle = Integer.parseInt(token.nextToken());
			Thread.sleep(1000);
			br = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(file)));
			token = new java.util.StringTokenizer(br.readLine());
			token.nextToken();
			int user2 = Integer.parseInt(token.nextToken());
			int nice2 = Integer.parseInt(token.nextToken());
			int sys2 = Integer.parseInt(token.nextToken());
			int idle2 = Integer.parseInt(token.nextToken());
			return (double) ((user2 + sys2 + nice2) - (user + system + nice))
					/ (float) ((user2 + nice2 + sys2 + idle2) - (user + nice + system + idle));
		} catch (Exception ex) {
			return (double) 0.0;
		}

	}

	final String substring(String src, int startidx, int endidx) {
		byte[] b = src.getBytes();
		StringBuffer sbr = new StringBuffer();
		for (int i = startidx; i <= endidx; i++) {
			sbr.append((char) b[i]);
		}
		return sbr.toString();
	}

	final long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			java.io.InputStreamReader ir = new java.io.InputStreamReader(proc.getInputStream());
			java.io.LineNumberReader input = new java.io.LineNumberReader(ir);
			String line = input.readLine();
			if (line == null || line.length() < 10) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}

				String caption = substring(line, capidx, cmdidx - 1).trim();
				String cmd = substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				if (caption.equals("System Idle Process") || caption.equals("System")) {
					idletime += Long.valueOf(substring(line, kmtidx, rocidx - 1).trim()).longValue();
					idletime += Long.valueOf(substring(line, umtidx, wocidx - 1).trim()).longValue();
					continue;
				}
				try {
					kneltime += Long.valueOf(substring(line, kmtidx, rocidx - 1).trim()).longValue();
					usertime += Long.valueOf(substring(line, umtidx, wocidx - 1).trim()).longValue();
				} catch (Exception e) {
					continue;
				}
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getStartMemory() {
		return startMemory;
	}

	public boolean isSun() {
		return System.getProperty("java.vm.vendor").indexOf("Sun") != -1;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void loadAddress() {
		try {
			java.net.InetAddress address = java.net.InetAddress.getLocalHost();
			java.net.InetAddress[] all = java.net.InetAddress.getAllByName(address.getHostName());
			for (int i = 0; i < all.length; i++) {
				String temp = null;
				temp = all[i].getHostAddress().toString();
				if (!temp.startsWith("127.0") && !temp.startsWith("169.254")) {
					serverIP = temp;
				}
			}
			serverName = address.getHostName();
		} catch (java.net.UnknownHostException e) {
		}
	}

	/**
	 * 获得所在服务器名称
	 */
	public String getServerName() {
		return serverName;
	}

	public String getServerIP() {
		return serverIP;
	}

	public boolean isSupportSpring() {
		return supportSpring;
	}

	public boolean isSupportLoonframework() {
		return supportLoonframework;
	}

	public boolean isSupportStruts() {
		return supportStruts;
	}

	public boolean isSupportJAF() {
		return supportJAF;
	}

	public boolean isSupportJavaxSql() {
		return supportJavaxSql;
	}

	public boolean isSupportHibernate() {
		return supportHibernate;
	}

	public boolean isSupportJNDI() {
		return supportJNDI;
	}

	public boolean isSupportMail() {
		return supportMail;
	}

	public boolean isSupportBeanUtils() {
		return supportBeanUtils;
	}

	public boolean isSupportCommonLogging() {
		return supportCommonLogging;
	}

	public boolean isSupportCommonCodec() {
		return supportCommonCodec;
	}

	public boolean isSupportCommonCollection() {
		return supportCommonCollection;
	}

	public boolean isSupportCommonDigester() {
		return supportCommonDigester;
	}

	public boolean isSupportCommonLang() {
		return supportCommonLang;
	}

	public boolean isSupportJakartaRegExp() {
		return supportJakartaRegExp;
	}

	public boolean isSupportLucene() {
		return supportLucene;
	}

	public boolean isSupportDom4j() {
		return supportDom4j;
	}

	public boolean isSupportMmMysqlDriver() {
		return supportMmMysqlDriver;
	}

	public boolean isSupportComMysqlDriver() {
		return supportComMysqlDriver;
	}

	public boolean isSupportImageProcessing() {
		return supportImageProcessing;
	}

	public String getDoubleOperation() {
		long begin = System.currentTimeMillis();
		int flag = 0;
		double random = (double) new java.util.Random().nextInt(1000);
		while (flag < 100000) {
			random = Math.sqrt(random);
			flag++;
		}
		long end = System.currentTimeMillis();
		long result = end - begin;
		return String.valueOf(result);
	}

	public String getNumberOperation() {
		long begin = startTime;
		int flag = 0;
		while (flag < 1000000) {
			flag++;
		}
		long end = System.currentTimeMillis();
		long result = end - begin;

		return String.valueOf(result);
	}
}