package action;

import core.WebApi;
import pages.common.CommonPopUp;
import pages.visitinglist.PatientSearchPopup;
import pages.visitinglist.ReceptionInfoPopup;
import pages.visitinglist.VisitingListPage;
import utils.PageFactoryManager;

public class CommonAction extends WebApi {

    public CommonAction selectPatient(String ptId) throws Exception{
        //Check if displayed in visiting list
        sleepTimeInSecond(3);
        boolean isDisplayed = PageFactoryManager.get(VisitingListPage.class)
                .verifyVisitingListPageDisplayed()
                .isPatientIdDisplayed(ptId);
        if (!isDisplayed) {
            PageFactoryManager.get(VisitingListPage.class)
                    .searchForPatientId(ptId);
            //Another check if displayed in visiting list after search
            isDisplayed = PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .isPatientIdDisplayed(ptId);
            if(!isDisplayed){
                PageFactoryManager.get(PatientSearchPopup.class)
                        .selectPatientId(ptId);
                PageFactoryManager.get(CommonPopUp.class)
                        .skipPopUps();
                PageFactoryManager.get(ReceptionInfoPopup.class)
                        .verifyReceptionInfoPopupDisplayed()
                        .uncheckAllCheckboxes()
                        .addPatientToReception();
            }
            PageFactoryManager.get(VisitingListPage.class)
                    .verifyVisitingListPageDisplayed()
                    .verifyPatientIdDisplayed(ptId);

        } else {
            PageFactoryManager.get(VisitingListPage.class)
                    .selectPatient(ptId);
        }
        return this;
    }
}
