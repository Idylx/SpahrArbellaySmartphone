/*
Author : Olivier Arbellay
Date: 14 mai 2017
*/
package chrono;

public class Chrono {

	private long timeBegin = 0;
	private long timeEnd = 0;
	private long breakBegin = 0;
	private long breakEnd = 0;
	private long period = 0;

	public void start() {
		timeBegin = System.currentTimeMillis();
		timeEnd = 0;
		breakBegin = 0;
		breakEnd = 0;
		period = 0;
	}

	public void stop() {
		if (timeBegin == 0) {
			return;
		}
		timeEnd = System.currentTimeMillis();
		period = (timeEnd - timeBegin) - (breakEnd - breakBegin);
		timeBegin = 0;
		timeEnd = 0;
		breakBegin = 0;
		breakEnd = 0;
	}

	public void pause() {
		if (timeBegin == 0) {
			return;
		}
		breakBegin = System.currentTimeMillis();
	}

	public void resume() {
		if (timeBegin == 0) {
			return;
		}
		if (breakBegin == 0) {
			return;
		}
		breakEnd = System.currentTimeMillis();
		timeBegin = timeBegin + breakEnd - breakBegin;
		timeEnd = 0;
		breakBegin = 0;
		breakEnd = 0;
		period = 0;
	}

	public long getPeriodSec() {
		return period / 1000;
	}

	public long getPeriodMs() {
		return period;
	}

	public String getPeriodTxt() {
		return timeToHMS(getPeriodSec());
	}

	public static String timeToHMS(long tempsS) {

		int h = (int) (tempsS / 3600);
		int m = (int) ((tempsS % 3600) / 60);
		int s = (int) (tempsS % 60);

		String r = "";

		if (h > 0) {
			r += h + " h ";
		}
		if (m > 0) {
			r += m + " min ";
		}
		if (s > 0) {
			r += s + " s";
		}
		if (h <= 0 && m <= 0 && s <= 0) {
			r = "0 s";
		}

		return r;
	}

}
