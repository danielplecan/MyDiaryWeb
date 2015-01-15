package mydiaryweb.module.mdqa.question_asking;

import java.security.Timestamp;
import java.util.Date;

import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.model.ActionDate;
import mydiaryweb.module.mdqa.model.Face;
import mydiaryweb.module.mdqa.model.Location;
import mydiaryweb.module.mdqa.model.Movement;
import mydiaryweb.module.mdqa.model.Sound;

import mydiaryweb.module.mdqa.util.RandomUtil;

public class QuestionObject {
	private Integer id;
	private Action additionalInformation; /*
										 * Additional information that will help
										 * us construct the question ex. What
										 * was that sound at [8:00] ?
										 */
	private Object requestedInformation; /*
										 * The infromation we need to find as an
										 * object ex. face, movment...
										 */
	private String requestedInformationName; /*
											 * Name of the information we need
											 * to find ex. face, movment...
											 */
	private Timestamp stamp; /*
							 * Timestamp to know if the question is still
							 * relevant private
							 */
	Date expiryTime;

	QuestionObject(Location location, Face face, Movement movement,
			Sound sound, ActionDate actionDate, Timestamp stamp, Date expiryTime) {
		this.setId(Integer.valueOf(RandomUtil.basicRandId()));

		setAdditionalInformation(location, face, movement, sound, actionDate);
		if (stamp != null && expiryTime != null) {
			setStamp(stamp);
			setExpiryTime(expiryTime);
		} else {
			/* expiryTime is set by default to 15 min */
			// stamp=
			// expiryTime=
		}
	}

	private void setId(Integer valueOf) {
		this.id = valueOf;

	}

	public Action getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Location location, Face face,
			Movement movement, Sound sound, ActionDate actionDate) {
		this.additionalInformation.setLocation(location);
		this.additionalInformation.setFace(face);
		this.additionalInformation.setMovement(movement);
		this.additionalInformation.setSound(sound);
		this.additionalInformation.setActionDate(actionDate);
	}

	public Object getRequestedInformation() {
		return requestedInformation;
	}

	public void setRequestedInformation(Object requestedInformation) {
		this.requestedInformation = requestedInformation;
	}

	public Timestamp getStamp() {
		return stamp;
	}

	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

}