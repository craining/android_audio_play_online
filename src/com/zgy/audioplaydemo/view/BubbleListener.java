package com.zgy.audioplaydemo.view;

/**
 * 回调
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
public interface BubbleListener {

	public void playFail(PlayBubble playBubble);

	public void playStoped(PlayBubble playBubble);

	public void playStart(PlayBubble playBubble);

	public void playCompletion(PlayBubble playBubble);

}
