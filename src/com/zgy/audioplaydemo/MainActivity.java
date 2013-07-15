package com.zgy.audioplaydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.zgy.audioplaydemo.view.BubbleListener;
import com.zgy.audioplaydemo.view.PlayBubble;

/**
 * 
 * @Description:
 * @author:zhuanggy
 * @see:
 * @since:
 * @Date:2013-5-29
 * 
 *                 欢迎加入QQ开发群：88130145
 * 
 */
public class MainActivity extends Activity {

	// 一下Url是百度音乐里随便找的，若demo始终无法正确运行，可尝试更换一个url
	private static final String shortSound = "http://mp3.g-smart.cn/ring/201041814441695.mp3";// 正确的url，时长较短
	private static final String url = "http://music.baidu.com/data/music/file?link=http://zhangmenshiting.baidu.com/data2/music/35419967/392535226800128.mp3?xcode=e7484eb7e3b9062f0ca177dffe5246f3";// 正确的url，时长较长
	private static final String urlError = "http://music.baidu.com/data/music/file?link=http://zhangmenshiting.baidu.com/data2/music/35419967/392535226800128.mp3?xc";// 错误的url

	private PlayBubble[] playBubble;
	private PlayBubble nowPlayBubble;// 当前播放的气泡，用于其它气泡开始播放时，关闭当前的

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout v = (LinearLayout) findViewById(R.id.layout_main_view);

		playBubble = new PlayBubble[5];
		for (int i = 0; i < 5; i++) {
			playBubble[i] = new PlayBubble(MainActivity.this);// 此处是动态添加气泡，也可在xml直接添加气泡控件<com.zgy.audioplaydemo.view.PlayBubble
																// ... />
			v.addView(playBubble[i]);
			playBubble[i].setBubbleListener(new BubbleListener() {

				@Override
				public void playStoped(PlayBubble playBubble) {
				}

				@Override
				public void playStart(PlayBubble playBubble) {
					// 判断之前是否有正在播放的，有则暂停
					if (nowPlayBubble != null && nowPlayBubble.getId() != playBubble.getId()) {
						nowPlayBubble.stopPlay();
					}
					nowPlayBubble = playBubble;
				}

				@Override
				public void playFail(PlayBubble playBubble) {
					Log.e("", "playFail id=" + playBubble.getId() + "  playFail url=" + playBubble.getUrl());
				}

				@Override
				public void playCompletion(PlayBubble playBubble) {
				}

			});
		}
		playBubble[0].setAudioUrl(url, 0);
		playBubble[1].setAudioUrl(urlError, 1);
		playBubble[2].setAudioUrl(shortSound, 2);
		playBubble[3].setAudioUrl(urlError, 3);
		playBubble[4].setAudioUrl(shortSound, 4);
	}

	@Override
	protected void onDestroy() {
		// 退出暂停播放
		if (nowPlayBubble != null) {
			nowPlayBubble.stopPlay();
		}
		super.onDestroy();
	}

}
