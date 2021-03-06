About Latens
======

Latens is an android application used to measure the end-to-end latency of touch interfaces such as tablets and smartphones under Android OS. The latency of an interactive system is the delay between a user's action and the corresponding feedback presented to the user. Latency on direct-touch system is easy to perceive: even a small delay of 10 ms results in a visible spatial offset between the finger and the virtual object controlled by the finger. For Android smartphones such as Samsung Galaxy S2 we have measured delais of about 130 ms. The more the latency is reduced, the better the fluidity feeling and user control.

The principles used in this tool to estimate latencies are based on the research work of François Bérard and Renaud Blanch (from LIG, University of Grenoble) in the following research publication: http://tripet.imag.fr/publs/2013/ITS13_Berard_LatencyEstimators.pdf

More precisely, we have implemented the Low Overhead (LO) approach, which does not require any additional equipment (such as an external calibrated camera) in addition of the touch device. In this approach, the user is asked to precisely follow with his finger a cursor that moves along a circular trajectory, at constant speed. In LO method the latency is computed at each display refresh, using the most recent touch event. The event provides the position of the finger which is converted into polar coordinates yielding the angle Ac. As this event was received while the display was showing the prescription at an angle of Ap, the latency estimate is given by : L = (Ap - Ac) / W
, where W is the angular speed. 

Our implementation is a bit different based on the use of a theoretical model of the cursor movement in the form of the angular function Ath(t), combined with a process running at best efforts, that continually updates the cursor's position, independently of the refresh period and of the touch event capturing. Thus, at each touch event we compute Ath(t) and we extract the angle Ac(t) corresponding to the current finger position supplied with a delay. The difference between the two is divided by the angular speed to get the instantaneous latency. We then use a linear filter to yield, over time, an average estimate of the latency.

Our method can be suited to different devices of various form factors and can take into account user's preferences. Its parameters are the following: Radius, Revolution period, Integrator time constant, Analysis duration.


Authors
=======

Christophe Maldivi and Eric Petit designed and implemented Latens. Christophe Maldivi is senior developer, Eric Petit is researcher in gestural interaction. Both work at Orange Labs Grenoble.


Feel free to contact the authors for any question related to this application and its purpose.

License
=======
Copyright (C) 2014 christophe.maldivi@orange.com, eric.petit@orange.com

Licensed under the GNU General Public License version 3.0

http://opensource.org/licenses/GPL-3.0


