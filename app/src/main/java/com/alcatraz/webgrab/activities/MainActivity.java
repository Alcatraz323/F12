package com.alcatraz.webgrab.activities;

import android.app.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.support.v7.widget.*;
import android.support.v4.widget.*;
import android.support.design.widget.*;
import android.view.View.*;
import android.support.v7.app.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import android.util.*;
import java.util.*;
import android.webkit.*;
import android.net.http.*;
import android.widget.TextView.*;
import android.view.inputmethod.*;
import android.graphics.*;
import android.net.*;
import java.text.*;
import android.widget.ExpandableListView.*;
import com.alcatraz.support.v4.appcompat.ViewPagerAdapter;
import com.alcatraz.support.v4.appcompat.AlertDialogUtil;
import android.widget.CompoundButton.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import android.content.res.*;
import android.support.v4.view.*;
import android.text.*;
import com.unnamed.b.atv.model.*;
import com.unnamed.b.atv.view.*;
import android.support.v7.view.*;
import com.alcatraz.webgrab.activities.*;
import com.alcatraz.webgrab.bean.*;
import com.alcatraz.webgrab.*;
import com.alcatraz.webgrab.adapters.*;
import android.support.v4.content.*;


public class MainActivity extends ThemedActivity 
{
	View v;
	android.support.v7.widget.Toolbar tb;
	AppBarLayout abl;
	public static final String THEME_ACTION="t";
	public static final String INSPECT_ACT="ins";
	public static final String UPDATE_ARGS="update_arg";
	DrawerLayout dl;
	UpdateThemeReceiver utr;
	AutoCompleteTextView et;
	LinkedList<String> style=new LinkedList<String>();
	StyleListenerAdaptet aaa;
	StyleListenerAdaptet aaa2;
	SwipeRefreshLayout srl;
	WebView4Scroll wv;
	String defaultua;
	String logmode;
	LinearLayout ll1;
	/*--LeftDrawer Widgets*/
	TextView titlev;
	TextView host;
	ImageView iconv;
	TextView white;
	TextView full_screen;
	TextView quan;
	LinearLayout ll;
	TextView avg_speed;
	ExpandableListView elv;
	String theme;
	/*--left_done---*/
	/*--left timer*/
	Date start;
	Date rt;
	Date finish;
	/*--left finish*/
	/*--right widgets--*/
	LinearLayout ll2;
	CheckBox cb;
	TabLayout tl;
	List<View> pager=new ArrayList<View>();
	ViewPager vpm;
	ViewPagerAdapter vpa;
	Spinner sp;
	CheckBox cb1;
	int d_count;
	ListView conslelist;
	ConsleAdapter ca;
	EditText coninput;
	android.webkit.CookieManager cm;
	ExpandableAdapter expa;
	AutoAdapter aa;
	String saved;
	DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	ProgressBar pb;
	double q_1;
	String current_html;
	boolean loadcurrent=false;
	Document html2jsoup;
	LinearLayout llins;
	//Right UserAgentPanel
	CardView cv1;
	TextView ua_curr;
	//Spf UA_DEFAULT
	String ua;
	//Major_Tree_Inspector;
	TreeNode html;
	AndroidTreeView atv;
	boolean needAnim=true;
	TreeNode root;
	View atv_v;
	List<TreeNode> filterred=new LinkedList<TreeNode>();
	AlcApi aaNa;
	android.webkit.CookieManager coom;
	android.support.v7.view.ActionMode gh;
	boolean picking=false;
	LinkedList<String> stylesheet=new LinkedList<String>();
	List<ConsoleMessage> condata=new ArrayList<ConsoleMessage>();
	List<String> hosts=new ArrayList<String>();
	Map<String,List<ResFiles>> data_1=new HashMap<String,List<ResFiles>>();
	WebViewClient wvc=new WebViewClient(){

		@Override
		public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request)
		{
			return super.shouldInterceptRequest(view, request);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
		{
			if (!picking)
			{
				view.loadUrl(request.getUrl().toString());
			}
			// TODO: Implement this method
			return super.shouldOverrideUrlLoading(view, request);
		}



		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
		{
			// TODO: Implement this method
			handler.proceed();
		}

		@Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event)
		{
			// TODO: Implement this method
			return super.shouldOverrideKeyEvent(view, event);
		}
		@Override
		public void onPageFinished(WebView view, String url)
		{
			// TODO: Implement this method
			super.onPageFinished(view, url);
			loadcurrent = true;
			srl.setRefreshing(false);
			calculateTimesSystem();
			pb.setVisibility(View.GONE);
			aaNa.WvLoadFunctionFrameWork();

		}
		@Override
		public void onLoadResource(WebView view, String url)
		{
			// TODO: Implement this method
			super.onLoadResource(view, url);
			ResFiles file=new ResFiles();
			file.setUrl(url);
			file.setHost(Analyser.getHost(url));
			file.setName(Analyser.getName(url));
			if (hosts.contains(file.getHost()))
			{
				data_1.get(file.getHost()).add(file);
			}
			else
			{
				hosts.add(file.getHost());
				List<ResFiles> l=new ArrayList<ResFiles>();
				l.add(file);
				data_1.put(file.getHost(), l);
			}
			expa.notifyDataSetChanged();
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			// TODO: Implement this method
			loadcurrent = false;
			data_1.clear();
			hosts.clear();
			condata.clear();
			ca.notifyDataSetChanged();
			expa.notifyDataSetChanged();
			stylesheet.clear();
			aaa2.notifyDataSetChanged();
			q_1 = usedQuality();
			start = new Date(System.currentTimeMillis());
			et.setText(url);
			try
			{
				String host1=new URL(url).getHost();
				host.setText(host1);
			}
			catch (Exception e)
			{}
			pb.setVisibility(View.VISIBLE);
			super.onPageStarted(view, url, favicon);
		}

	};
	Handler main=new Handler(){

		@Override
		public void handleMessage(Message msg)
		{
			setTreeView(root, true);
			// TODO: Implement this method
			super.handleMessage(msg);
		}

	};
	WebChromeClient wcc=new WebChromeClient(){

		@Override
		public void onProgressChanged(WebView view, int newProgress)
		{
			// TODO: Implement this method
			super.onProgressChanged(view, newProgress);
			pb.setProgress(newProgress);

		}

		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			// TODO: Implement this method
			super.onReceivedTitle(view, title);
			//view.loadUrl("javascript:document.body.addEventListener(\"click\",console.log(window.event.srcElement.tagName));");
			saved = (String) getUtils().getPreference(Utils.PreferenceType.STRING, "saved", "");
			String bean=title + ":" + view.getUrl();
			if (!saved.contains(bean))
			{
				saved = saved + bean + "_&-__";
				getUtils().savePreference(Utils.PreferenceType.STRING, "saved", saved);
				aa.updateSaved();
			}


			titlev.setText(title);
			rt = new Date(System.currentTimeMillis());
			white.setText(getString(R.string.l_3) + ":" + (rt.getTime() - start.getTime()) + " ms");
		}

		@Override
		public void onReceivedIcon(WebView view, Bitmap icon)
		{
			// TODO: Implement this method
			super.onReceivedIcon(view, icon);
			iconv.setImageBitmap(icon);
		}

		@Override
		public boolean onConsoleMessage(ConsoleMessage consoleMessage)
		{
			condata.add(consoleMessage);
			ca.notifyDataSetChanged();
			// TODO: Implement this method
			return true;
		}

	};
	public void addNode(int depth, TreeNode G, List<Node> e)
	{
		for (Node i:e)
		{
			if ((!i.nodeName().contains("#")) || i.nodeName().contains("#comment") || i.nodeName().contains("#data"))
			{
				Bean b=new Bean(i, depth, false);
				TreeNode cu=new TreeNode(b).setViewHolder(new TreeHolder(this));
				addNode(depth + 1, cu, i.childNodes());
				G.addChild(cu);

			}
		}
	}

	class JavaScriptCallback
	{
		@JavascriptInterface
		public void getSource(String html)
		{
			current_html = html;
			runOnUiThread(new Runnable(){

					@Override
					public void run()
					{
						inspect();

						// TODO: Implement this method
					}
				});

		}

		@JavascriptInterface
		public void getElement(final String outH, final String tag)
		{
			style.clear();
			runOnUiThread(new Runnable(){

					@Override
					public void run()
					{
						showPopup(outH, tag);
						// TODO: Implement this method
					}
				});
		}
		@JavascriptInterface
		public void getCss(String i)
		{
			style.add(i);
			aaa.notifyDataSetChanged();
		}
		@JavascriptInterface
		public void styleSheet(final String sty)
		{
			runOnUiThread(new Runnable(){

					@Override
					public void run()
					{
						stylesheet.add(sty);
						aaa2.notifyDataSetChanged();
						// TODO: Implement this method
					}
				});

		}
	}


	interface call
	{
		void onload(String html);
	}

	public void showPopup(final String i, String tag)
	{
		gh.finish();
		// TODO: Implement this method
		View v=getLayoutInflater().inflate(R.layout.inspect_el_popup, null);
		TextView ti=(TextView) v.findViewById(R.id.inspectelpopupTextView1);
		Button connect_r_p=(Button) v.findViewById(R.id.inspectelpopupButton1);
		TextView source=(TextView) v.findViewById(R.id.inspectelpopupTextView2);
		ListView li=(ListView) v.findViewById(R.id. inspectelpopupListView1);
		Document el=Jsoup.parse(i);
		Element e=el.getElementsByTag(tag).get(0);
		ti.setText(e.tagName());
		source.setText(i);
		li.setAdapter(aaa);
		final android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this).setView(v).show();
		connect_r_p.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					filterred.clear();
					inspector_check_hi(i);
					a.dismiss();
					// TODO: Implement this method
				}
			});
	}
	public void trav(String key, TreeNode tn)
	{
		List<TreeNode> chil=tn.getChildren();
		for (TreeNode tbxxh:chil)
		{
			Bean b=(Bean) tbxxh.getValue();
			Node bj=b.getData();
			if (TreeHolder.cutHeader(bj.outerHtml()).equals(key))
			{
				filterred.add(tbxxh);
			}
			//Log.e("Alc_f12","size:"+filterred.size()+"  trr="+TreeHolder.cutHeader(bj.outerHtml())+" key="+key);
			trav(key,tbxxh);
			
		}
		
	}
	public void setHighlightNode()
	{
		dl.openDrawer(Gravity.RIGHT);
	}
	public void inspector_check_hi(String hi)
	{
		String process=TreeHolder.cutHeader(hi);
		String state="";
		Log.e("Alc_trav","before");
		trav(process, root);
		for(TreeNode n:filterred){
			state+=n.getPath();
		}
		atv.expandPath(state);
		//main.sendMessage(main.obtainMessage());
		//setTreeView(root,true);
		setHighlightNode();
	}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		aaa = new StyleListenerAdaptet(style, this);
		aaa2 = new StyleListenerAdaptet(stylesheet, this);
		initInspector();
		initRight();
		initViews();
		if (ua == "" || ua == null)
		{
			getUtils().savePreference(Utils.PreferenceType.STRING, "user_agent", wv.getSettings().getUserAgentString());
		}
		ua = getUtils().getPreference(Utils.PreferenceType.STRING, "user_agent", wv.getSettings().getUserAgentString()).toString();
		needAnim=getUtils().getPreference(Utils.PreferenceType.BOOLEAN,"inspect_anim",needAnim);
		ua_curr.setText(ua);
		wv.getSettings().setUserAgentString(ua);
		initLeft();
		regist();
		updCur();
		if (getIntent().getAction() == Intent.ACTION_VIEW)
		{
			if (getIntent().getData() != null)
			{
				wv.loadUrl(getIntent().getData().toString());
			}
			
		}
		if(getIntent().getAction()==Intent.ACTION_SEND){
			String hlllll=getIntent().getStringExtra(Intent.EXTRA_TEXT);
			if((hlllll!=null&&!hlllll.equals(""))){
				String[] g=hlllll.split(" ");
				wv.loadUrl(g[g.length-1]);
			}
		}
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack())
		{       
            wv.goBack();       
            return true;       
        }       
        return super.onKeyDown(keyCode, event);       
		// TODO: Implement this method

	}
	public void calculateTimesSystem()
	{
		finish = new Date(System.currentTimeMillis());
		double tra=usedQuality() - q_1;
		quan.setText(getString(R.string.l_1) + ":" + tra + " bytes");
		double htime=finish.getTime() - start.getTime();
		avg_speed.setText(getString(R.string.l_2) + ":" + (int)(tra / htime) + " b/ms");
		full_screen.setText(getString(R.string.l_6) + ":" + htime + " ms");
	}
	public void initViews()
	{
		coom = android.webkit.CookieManager.getInstance();
		theme = (String) getUtils().getPreference(Utils.PreferenceType.STRING, "theme", "blue");
		v = findViewById(R.id.mainView1);
		atv = new AndroidTreeView(this, null);
		aa = new AutoAdapter(getUtils(), this, getRgb(), theme);
		et = (AutoCompleteTextView) findViewById(R.id.mainEditText1);
		et.setAdapter(aa);
		et.setOnEditorActionListener(new OnEditorActionListener(){

				@Override
				public boolean onEditorAction(TextView p1, int p2, KeyEvent p3)
				{
					if (p2 == EditorInfo.IME_ACTION_DONE)
					{
						srl.setEnabled(true);
						String url=et.getText().toString();
						if (!url.startsWith("http://") && !url.startsWith("https://"))
						{
							url = "http://" + url;
						}
						InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
						if (imm != null)
						{ 

							imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),  
														0); 
						}
						wv.loadUrl(url);

					}
					// TODO: Implement this method
					return true;
				}
			});
		pb = (ProgressBar) findViewById(R.id.mainProgressBar1);
		srl = (SwipeRefreshLayout) findViewById(R.id.drawerfileSwipeRefreshLayout1);
		srl.setColorSchemeColors(getRgb());
		srl.setEnabled(false);
		tl = (TabLayout) findViewById(R.id.mainTabLayout1);
		vpm = (ViewPager) findViewById(R.id.mainViewPagerMin1);
		List<String> tabt=new ArrayList<String>();
		tabt.add("Element");
		tabt.add("Console");
		vpa = new ViewPagerAdapter(pager, tabt);
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
		tl.setPadding(tl.getPaddingLeft(), tl.getPaddingTop() + statusBarHeight, tl.getPaddingRight(), tl.getPaddingBottom());
		vpm.setAdapter(vpa);
		tl.setupWithViewPager(vpm);
		srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

				@Override
				public void onRefresh()
				{

					wv.reload();
					// TODO: Implement this method
				}
			});
		abl = (AppBarLayout) findViewById(R.id.mainAppBarLayout1);
		tb = (android.support.v7.widget.Toolbar) findViewById(R.id.mainToolbar1);
		dl = (DrawerLayout) findViewById(R.id.mainDrawerLayout1);
		dl.addDrawerListener(new DrawerLayout.DrawerListener(){

				@Override
				public void onDrawerSlide(View p1, float p2)
				{
					// TODO: Implement this method
				}

				@Override
				public void onDrawerOpened(View p1)
				{
					p1.setClickable(true);
					// TODO: Implement this method
				}

				@Override
				public void onDrawerClosed(View p1)
				{

					// TODO: Implement this method
				}

				@Override
				public void onDrawerStateChanged(int p1)
				{
					// TODO: Implement this method
				}
			});
		dl.setDrawerLockMode(dl.LOCK_MODE_LOCKED_CLOSED);
		tb.setTitle(R.string.app_name);
		setSupportActionBar(tb);
		setupMaterialWithDrawer(dl, tb, v);
		if (Build.VERSION.SDK_INT < 20)
		{
			v.setVisibility(View.GONE);
		}

		wv = (WebView4Scroll) findViewById(R.id.mainWebView1);
		wv.addJavascriptInterface(new JavaScriptCallback(), "local_obj");
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setSupportZoom(true);
		wv.setWebChromeClient(wcc);
		wv.setWebViewClient(wvc);
		wv.getSettings().enableSmoothTransition();
		wv.getSettings().setUseWideViewPort(true);
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setDisplayZoomControls(false);
		wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
		wv.setHorizontalScrollBarEnabled(false);
		wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		wv.getSettings().setLoadWithOverviewMode(true);
		aaNa = new AlcApi();
		//dc=new DeamonClient(wv);
		aaNa.WvInit(wv);
	}

	public void initLeft()
	{

		titlev = (TextView) findViewById(R.id.leftTextView1);
		host = (TextView) findViewById(R.id.leftTextView8);
		white = (TextView) findViewById(R.id.leftTextView4);
		full_screen = (TextView) findViewById(R.id.leftTextView7);
		quan = (TextView) findViewById(R.id.leftTextView2);
		avg_speed = (TextView) findViewById(R.id.leftTextView3);
		iconv = (ImageView) findViewById(R.id.leftImageView1);
		ll = (LinearLayout) findViewById(R.id.leftLinearLayout1);
		ll1 = (LinearLayout) findViewById(R.id.mainLinearLayout2);

		if (theme.equals("night"))
		{
			ll1.setBackgroundColor(getRgb());
		}
		elv = (ExpandableListView) findViewById(R.id.leftExpandableListView1);
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
		ll.setPadding(0, statusBarHeight, 0, 0);
		expa = new ExpandableAdapter(this, hosts, data_1);
		elv.setAdapter(expa);
		elv.setOnChildClickListener(new OnChildClickListener(){

				@Override
				public boolean onChildClick(ExpandableListView p1, View p2, int p3, int p4, long p5)
				{
					// TODO: Implement this method
					showpreview(p3, p4);
					return true;
				}
			});
	}

	public void initInspector()
	{
		View inspect=getLayoutInflater().inflate(R.layout.inspect_element, null);
		llins = (LinearLayout) inspect.findViewById(R.id.inspectelementLinearLayout1);
		ListView styles=(ListView) inspect.findViewById(R.id.inspectelementListView2);
		styles.setAdapter(aaa2);
		Button btn=(Button) inspect.findViewById(R.id.inspectelementButton1);
		btn.setText(btn.getText()+"\n"+getString(R.string.inspect_Summ));
		btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					dl.closeDrawer(Gravity.RIGHT);
					startSupportActionMode(new android.support.v7.view.ActionMode.Callback(){

							@Override
							public boolean onCreateActionMode(final android.support.v7.view.ActionMode p1, Menu p2)
							{
								//toastOut(atv.getSaveState());
								aaNa.WvStartElementPicking();
								p1.setTitle(R.string.inspect_mode);
								picking = true;
								gh = p1;
								// TODO: Implement this method
								return true;
							}

							@Override
							public boolean onPrepareActionMode(android.support.v7.view.ActionMode p1, Menu p2)
							{
								// TODO: Implement this method
								return true;
							}

							@Override
							public boolean onActionItemClicked(android.support.v7.view.ActionMode p1, MenuItem p2)
							{
								// TODO: Implement this method
								return false;
							}

							@Override
							public void onDestroyActionMode(android.support.v7.view.ActionMode p1)
							{
								aaNa.WvStopElementPicking();
								// TODO: Implement this method
							}
						});
					// TODO: Implement this method
				}
			});
		btn.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					
					// TODO: Implement this method
					return true;
				}
			});
		pager.add(inspect);
	}
	public void initRight()
	{
		View right_console=getLayoutInflater().inflate(R.layout.right, null);
		sp = (Spinner)right_console.findViewById(R.id.rightSpinner1);
		final ArrayAdapter spad=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"ERROR","WARNING","TIP","LOG"});
		sp.setAdapter(spad);
		sp.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					logmode = (String) spad.getItem(p3);
					// TODO: Implement this method
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}
			});
		conslelist = (ListView)right_console.findViewById(R.id.rightListView1);
		cb1 = (CheckBox)right_console.findViewById(R.id.rightCheckBox2);
		cb1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2)
				{
					if (p2)
					{
						aaNa.WvStartEditMode();
					}
					else
					{
						aaNa.WvStopEditMode();
					}
					// TODO: Implement this method
				}
			});
		ca = new ConsleAdapter(this, condata);
		conslelist.setAdapter(ca);
		ll2 = (LinearLayout)right_console. findViewById(R.id.mainLinearLayout3);
		cv1 = (CardView)right_console. findViewById(R.id.listcardCard);
		cv1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					showUAPanel();
					// TODO: Implement this method
				}
			});
		ua_curr = (TextView)right_console. findViewById(R.id.rightTextView1);
		coninput = (EditText)right_console. findViewById(R.id.rightEditText1);
		coninput.setOnEditorActionListener(new OnEditorActionListener(){

				@Override
				public boolean onEditorAction(TextView p1, int p2, KeyEvent p3)
				{
					if ((coninput.getText().toString() != null) && !(coninput.getText().toString().equals("")))
					{
						wv.loadUrl(Analyser.getJavaScript(logmode) + "\"" + coninput.getText().toString() + "\")");
						coninput.setText(null);
					}

					// TODO: Implement this method
					return true;
				}
			});
		pager.add(right_console);
	}
	public void showUAPanel()
	{
		ua = (String) getUtils().getPreference(Utils.PreferenceType.STRING, "user_agent", ua);
		View root=getLayoutInflater().inflate(R.layout.useragent, null);
		RadioButton rb1=(RadioButton) root.findViewById(R.id.useragentRadioButton1);
		RadioButton rb2=(RadioButton) root.findViewById(R.id.useragentRadioButton2);
		if (ua.equals(wv.getSettings().getDefaultUserAgent(this)))
		{
			rb1.setChecked(true);
		}
		else if (ua.equals("User-Agent,Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11"))
		{
			rb2.setChecked(true);
		}
		final TextInputLayout til=(TextInputLayout) root.findViewById(R.id.textInputLayoutua);
		til.getEditText().setText(ua);
		rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2)
				{
					if (p2)
					{
						til.getEditText().setText(wv.getSettings().getDefaultUserAgent(MainActivity.this));
					}
					// TODO: Implement this method
				}
			});
		rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

				@Override
				public void onCheckedChanged(CompoundButton p1, boolean p2)
				{
					if (p2)
					{
						til.getEditText().setText("User-Agent,Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11");
					}
					// TODO: Implement this method
				}
			});

		android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.r_useragent)
			.setView(root)
			.setNegativeButton(R.string.ad_nb1, null)
			.setPositiveButton(R.string.ad_pb1, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					String reoua=til.getEditText().getText().toString();
					ua_curr.setText(reoua);
					getUtils().savePreference(Utils.PreferenceType.STRING, "user_agent", reoua);
					wv.getSettings().setUserAgentString(reoua);
					// TODO: Implement this method
				}
			})
			.create();
		new AlertDialogUtil().setSupportDialogColor(a, Color.parseColor("#3f51b5"));
		a.show();
	}
	public void showpreview(int group, int child)
	{
		String host=hosts.get(group);
		ResFiles tnep=data_1.get(hosts.get(group)).get(child);
		final String url=tnep.getUrl();
		View v=getLayoutInflater().inflate(R.layout.pre_show, null);
		WebView vg=(WebView) v.findViewById(R.id.preshowWebView1);
		TextView txvurl=(TextView) v.findViewById(R.id.preshowTextView1);
		txvurl.setText(url);
		vg.loadUrl(url);
		android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(host)
			.setView(v)
			.setNegativeButton(R.string.pre_nb, null)
			.setPositiveButton(R.string.pre_pb, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					showsingleDownload(url);
					// TODO: Implement this method
				}
			})
			.create();
		new AlertDialogUtil().setSupportDialogColor(a, getRgb());
		a.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mi=new MenuInflater(this);
		mi.inflate(R.menu.main_menu, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}
	public void regist()
	{
		IntentFilter ifil=new IntentFilter();
		ifil.addAction(THEME_ACTION);
		ifil.addAction(UPDATE_ARGS);
		ifil.addAction(INSPECT_ACT);
		utr = new UpdateThemeReceiver();
		registerReceiver(utr, ifil);
	}
	public void inspect()
	{
		Document doc=Jsoup.parse(current_html.replace("'", "\""));
		final Element html_root=doc.getElementsByTag("html").get(0);
		root = TreeNode.root();
		Bean b=new Bean(html_root, 0, false);
		html = new TreeNode(b).setViewHolder(new TreeHolder(this));
		//htmlTags(html_root.children(),new StringBuilder());
		addNode(1, html, html_root.childNodes());
		root.addChild(html);
		setTreeView(root, false);

	}
	public void setTreeView(TreeNode n, boolean isUpa)
	{
		atv=new AndroidTreeView(this,n);
		if(needAnim){
		atv.setDefaultAnimation(true);
		atv.setUse2dScroll(true);
		}
		llins.removeAllViews();
		llins.addView(atv.getView());
		// TODO: Implement this method
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Output.write(new File(Output.root + "cache.html"), current_html);
		switch (item.getItemId())
		{

			case R.id.item1:
				startActivity(new Intent(this, Preferences.class));
				break;
			case R.id.item2:
				//inspect();
				startActivity(new Intent(this, Author.class));
				break;
			case R.id.item3:
				if (wv.getUrl() != null && wv.getUrl() != "")
				{
					getUtils().showCookieDialog(this, wv.getUrl(), wv, false);
				}
				else
				{
					toastOut(getString(R.string.load_error));
				}
				break;

			case R.id.item5:
				if (loadcurrent)
				{

					toastOut(getString(R.string.wait));
					startActivity(new Intent(MainActivity.this, Page.class));
				}
				else
				{
					toastOut(getString(R.string.load_error));
				}
				break;
			case R.id.item4:
				dl.openDrawer(Gravity.LEFT);
				break;
			case R.id.item6:
				dl.openDrawer(Gravity.RIGHT);
				break;

		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}



	interface DialogTransmit
	{
		public void onPositive();
	}
	@Override
	protected void onDestroy()
	{
		unregisterReceiver(utr);
		// TODO: Implement this method
		super.onDestroy();
	}

	class UpdateThemeReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context p1, Intent p2)
		{
			if (p2.getAction().equals(THEME_ACTION))
			{
				System.exit(0);
			}
			else if (p2.getAction().equals(INSPECT_ACT))
			{
				runOnUiThread(new Runnable(){

						@Override
						public void run()
						{
							setTreeView(root, true);
							// TODO: Implement this method
						}
					});


			}else if(p2.getAction().equals(UPDATE_ARGS)){
				needAnim=getUtils().getPreference(Utils.PreferenceType.BOOLEAN,"inspect_anim",needAnim);
			}
		}
	}
	public double usedQuality()
	{
		int uId=android.os.Process.myUid();
		long rx=TrafficStats.getUidRxBytes(uId); 
		long tx=TrafficStats.getUidTxBytes(uId); 
		if (rx < 0 || tx < 0)
		{ 
			return 0;
		}
		else
		{ 
			return rx + tx;
		} 
	}
	public void showsingleDownload(final String url)
	{
		View v=getLayoutInflater().inflate(R.layout.download_single, null);
		final TextInputLayout til=(TextInputLayout) v.findViewById(R.id.textInputLayout12);
		til.getEditText().setText(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Analyser.getHost(url) + "/");
		final android.support.v7.app.AlertDialog a=new android.support.v7.app.AlertDialog.Builder(this)
			.setTitle(R.string.pre_pb)
			.setView(v)
			.setNegativeButton(R.string.ad_nb1, null)
			.setPositiveButton(R.string.pre_pb, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					download(url, til.getEditText().getText().toString(), new downloadCallBack(){

							@Override
							public void onComplete()
							{
								toastOut(getString(R.string.download_complete));
								// TODO: Implement this method
							}

							@Override
							public void onFailed()
							{
								toastOut(getString(R.string.download_fail));
								// TODO: Implement this method
							}
						});
					// TODO: Implement this method
				}
			})
			.create();

		new AlertDialogUtil().setSupportDialogColor(a, getRgb());
		a.show();
	}
	public void updCur()
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction(Page.done);
		BroadcastReceiver receiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent)
			{
				wv.loadData(Output.read(new File(Output.root + "cache.html")), "text/html", "utf-8");
			}
		};
		registerReceiver(receiver, filter);
	}
	public void toastOut(String c)
	{
		Toast.makeText(this, c, Toast.LENGTH_SHORT).show();
	}
	public void download(final String url1, final String dir, final downloadCallBack call)
	{
		final DownloadManager dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		Uri uri = Uri.parse(url1);
		DownloadManager.Request request = new DownloadManager.Request(uri);
		File dire=new File(dir);
		dire.mkdirs();
		File f=new File(dire.getAbsolutePath() + "/" + Analyser.getName(url1));
		request.setDestinationUri(Uri.fromFile(f));
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		request.allowScanningByMediaScanner();
		request.setVisibleInDownloadsUi(true);
		final long refernece = dManager.enqueue(request);
		IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
		BroadcastReceiver receiver = new BroadcastReceiver() {

			public void onReceive(Context context, Intent intent)
			{
				long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				if (refernece == myDwonloadID)
				{
					call.onComplete();
				}
			}
		};
		registerReceiver(receiver, filter);
	}

	public void show(String filesPath)
	{
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		startActivity(showOpenTypeDialog(filesPath));
	} 

    public static Intent showOpenTypeDialog(String param)
	{
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }
	public interface downloadCallBack
	{
		public void onComplete();
		public void onFailed();
	}
}
