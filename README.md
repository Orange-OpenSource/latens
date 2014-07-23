About Latens
======

Latens is an android application used to measure the end-to-end latency of touch interfaces such as tablets and smartphones under Android OS. The latency of an interactive system is the delay between a user's action and the corresponding feedback presented to the user. Latency on direct-touch system is easy to perceive: even a small delay of 10 ms results in a visible spatial offset between the finger and the virtual object controlled by the finger. For Android smartphones such as Samsung Galaxy S2 we have measured delais of about 130 ms. The more the latency is reduced, the better the fluidity feeling and user control.

The principles used in this tool to estimate latencies are based on the research work of François Bérard and Renaud Blanch (from LIG, University of Grenoble) in the following research publication: http://tripet.imag.fr/publs/2013/ITS13_Berard_LatencyEstimators.pdf

More precisely, we have implemented the Low Overhead (LO) approach, which does not require any additional equipment (such as an external calibrated camera) in addition of the touch device. In this approach, the user is asked to precisely follow with his finger a cursor that moves along a circular trajectory, at constant speed. In LO method the latency is computed at each display refresh, using the most recent touch event. The event provides the position of the finger which is converted into polar coordinates yielding the angle Ac. As this event was received while the display was showing the prescription at an angle of Ap, the estimate is given by : L = (Ap - Ac) / W
, where W is the angular speed. 

Our implementation is a bit different based on the use of the theorical model of the cursor movement in the form of the angular function Ath(t). Thus, at each touch event we compute Ath(t) and the angle Ac(t) corresponding to the finger position. The difference between the two is divided by the angular speed to get the instantaneous latency. We then use an IIR filter estimator to extract an average estimate of it over time.


Authors
=======

Christophe Maldivi and Eric Petit designed and implemented Latens.

Feel free to contact the authors for any question related to this application and its purpose.

License
=======
Copyright (C) 2014 christophe.maldivi@orange.com, eric.petit@orange.com

Licensed under the GNU General Public License version 3.0

http://opensource.org/licenses/GPL-3.0


