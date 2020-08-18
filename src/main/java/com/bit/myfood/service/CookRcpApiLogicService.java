package com.bit.myfood.service;

import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.network.Header;
import com.bit.myfood.model.network.Pagination;
import com.bit.myfood.model.network.request.CookRcpApiRequest;
import com.bit.myfood.model.network.response.CookRcpApiResponse;
import com.bit.myfood.repository.AdminUserRepository;
import com.bit.myfood.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CookRcpApiLogicService extends BaseService<CookRcpApiRequest,CookRcpApiResponse, CookRcp> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    // region CRUD
    @Override
    public Header<CookRcpApiResponse> create(Header<CookRcpApiRequest> request) {
        CookRcpApiRequest body = request.getData();

        CookRcp entity = CookRcp.builder()
                .rcpNm(body.getRcpNm())
                .rcpWay2(body.getRcpWay2())
                .rcpPat2(body.getRcpPat2())
                .infoEng(body.getInfoEng())
                .infoCar(body.getInfoCar())
                .infoPro(body.getInfoPro())
                .infoFat(body.getInfoFat())
                .infoNa(body.getInfoNa())
                .hashTag(body.getHashTag())
                .attFileNoMain(body.getAttFileNoMain())
                .attFileNoMk(body.getAttFileNoMk())
                .rcpPartsDtls(body.getRcpPartsDtls())
                .manual01(body.getManual01())
                .manualImg01(body.getManualImg01())
                .manual02(body.getManual02())
                .manualImg02(body.getManualImg02())
                .manual03(body.getManual03())
                .manualImg03(body.getManualImg03())
                .manual04(body.getManual04())
                .manualImg04(body.getManualImg04())
                .manual05(body.getManual05())
                .manualImg05(body.getManualImg05())
                .manual06(body.getManual06())
                .manualImg06(body.getManualImg06())
                .manual07(body.getManual07())
                .manualImg07(body.getManualImg07())
                .manual08(body.getManual08())
                .manualImg08(body.getManualImg08())
                .manual09(body.getManual09())
                .manualImg09(body.getManualImg09())
                .manual10(body.getManual10())
                .manualImg10(body.getManualImg10())
                .manual11(body.getManual11())
                .manualImg11(body.getManualImg11())
                .manual12(body.getManual12())
                .manualImg12(body.getManualImg12())
                .manual13(body.getManual13())
                .manualImg13(body.getManualImg13())
                .manual14(body.getManual14())
                .manualImg14(body.getManualImg14())
                .manual15(body.getManual15())
                .manualImg15(body.getManualImg15())
                .manual16(body.getManual16())
                .manualImg16(body.getManualImg16())
                .manual17(body.getManual17())
                .manualImg17(body.getManualImg17())
                .manual18(body.getManual18())
                .manualImg18(body.getManualImg18())
                .manual19(body.getManual19())
                .manualImg19(body.getManualImg19())
                .manual20(body.getManual20())
                .manualImg20(body.getManualImg20())
                .rcpSeq(body.getRcpSeq())
                .adminUser(adminUserRepository.getOne(body.getAdminUserId()))
                .build();

        CookRcp newEntity = baseRepository.save(entity);

        return Header.OK(response(newEntity));
    }

    @Override
    public Header<CookRcpApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(entity -> response(entity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CookRcpApiResponse> update(Header<CookRcpApiRequest> request) {

        CookRcpApiRequest body = request.getData();

        Optional<CookRcp> optionalEntity = baseRepository.findById(body.getId());


        return optionalEntity.map(entity -> {
            entity.setRcpNm(body.getRcpNm())
                    .setRcpWay2(body.getRcpWay2())
                    .setRcpPat2(body.getRcpPat2())
                    .setInfoEng(body.getInfoEng())
                    .setInfoCar(body.getInfoCar())
                    .setInfoPro(body.getInfoPro())
                    .setInfoFat(body.getInfoFat())
                    .setInfoNa(body.getInfoNa())
                    .setHashTag(body.getHashTag())
                    .setAttFileNoMain(body.getAttFileNoMain())
                    .setAttFileNoMk(body.getAttFileNoMk())
                    .setRcpPartsDtls(body.getRcpPartsDtls())
                    .setManual01(body.getManual01())
                    .setManualImg01(body.getManualImg01())
                    .setManual02(body.getManual02())
                    .setManualImg02(body.getManualImg02())
                    .setManual03(body.getManual03())
                    .setManualImg03(body.getManualImg03())
                    .setManual04(body.getManual04())
                    .setManualImg04(body.getManualImg04())
                    .setManual05(body.getManual05())
                    .setManualImg05(body.getManualImg05())
                    .setManual06(body.getManual06())
                    .setManualImg06(body.getManualImg06())
                    .setManual07(body.getManual07())
                    .setManualImg07(body.getManualImg07())
                    .setManual08(body.getManual08())
                    .setManualImg08(body.getManualImg08())
                    .setManual09(body.getManual09())
                    .setManualImg09(body.getManualImg09())
                    .setManual10(body.getManual10())
                    .setManualImg10(body.getManualImg10())
                    .setManual11(body.getManual11())
                    .setManualImg11(body.getManualImg11())
                    .setManual12(body.getManual12())
                    .setManualImg12(body.getManualImg12())
                    .setManual13(body.getManual13())
                    .setManualImg13(body.getManualImg13())
                    .setManual14(body.getManual14())
                    .setManualImg14(body.getManualImg14())
                    .setManual15(body.getManual15())
                    .setManualImg15(body.getManualImg15())
                    .setManual16(body.getManual16())
                    .setManualImg16(body.getManualImg16())
                    .setManual17(body.getManual17())
                    .setManualImg17(body.getManualImg17())
                    .setManual18(body.getManual18())
                    .setManualImg18(body.getManualImg18())
                    .setManual19(body.getManual19())
                    .setManualImg19(body.getManualImg19())
                    .setManual20(body.getManual20())
                    .setManualImg20(body.getManualImg20())
                    .setAdminUser(adminUserRepository.getOne(body.getAdminUserId()));
            return entity;
        })
                .map(entity -> baseRepository.save(entity))
                .map(newEntity -> response(newEntity))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<CookRcp> optionalEntity = baseRepository.findById(id);

        return optionalEntity.map(entity -> {
            baseRepository.delete(entity);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    // endregion

    @Override
    protected CookRcpApiResponse response(CookRcp entity) {
        // cookRcp -> cookRcpApiResponse

        CookRcpApiResponse cookRcpApiResponse = CookRcpApiResponse.builder()
                .id(entity.getId())
                .rcpNm(entity.getRcpNm())
                .rcpWay2(entity.getRcpWay2())
                .rcpPat2(entity.getRcpPat2())
                .infoEng(entity.getInfoEng())
                .infoCar(entity.getInfoCar())
                .infoPro(entity.getInfoPro())
                .infoFat(entity.getInfoFat())
                .infoNa(entity.getInfoNa())
                .hashTag(entity.getHashTag())
                .attFileNoMain(entity.getAttFileNoMain())
                .attFileNoMk(entity.getAttFileNoMk())
                .rcpPartsDtls(entity.getRcpPartsDtls())
                .manual01(entity.getManual01())
                .manualImg01(entity.getManualImg01())
                .manual02(entity.getManual02())
                .manualImg02(entity.getManualImg02())
                .manual03(entity.getManual03())
                .manualImg03(entity.getManualImg03())
                .manual04(entity.getManual04())
                .manualImg04(entity.getManualImg04())
                .manual05(entity.getManual05())
                .manualImg05(entity.getManualImg05())
                .manual06(entity.getManual06())
                .manualImg06(entity.getManualImg06())
                .manual07(entity.getManual07())
                .manualImg07(entity.getManualImg07())
                .manual08(entity.getManual08())
                .manualImg08(entity.getManualImg08())
                .manual09(entity.getManual09())
                .manualImg09(entity.getManualImg09())
                .manual10(entity.getManual10())
                .manualImg10(entity.getManualImg10())
                .manual11(entity.getManual11())
                .manualImg11(entity.getManualImg11())
                .manual12(entity.getManual12())
                .manualImg12(entity.getManualImg12())
                .manual13(entity.getManual13())
                .manualImg13(entity.getManualImg13())
                .manual14(entity.getManual14())
                .manualImg14(entity.getManualImg14())
                .manual15(entity.getManual15())
                .manualImg15(entity.getManualImg15())
                .manual16(entity.getManual16())
                .manualImg16(entity.getManualImg16())
                .manual17(entity.getManual17())
                .manualImg17(entity.getManualImg17())
                .manual18(entity.getManual18())
                .manualImg18(entity.getManualImg18())
                .manual19(entity.getManual19())
                .manualImg19(entity.getManualImg19())
                .manual20(entity.getManual20())
                .manualImg20(entity.getManualImg20())
                .rcpSeq(entity.getRcpSeq())
                .adminUserId(entity.getAdminUser().getId())
                .build();

        return cookRcpApiResponse;
    }

    @Override
    public Header<List<CookRcpApiResponse>> search(Pageable pageable) {
        Page<CookRcp> entities = baseRepository.findAll(pageable);

        List<CookRcpApiResponse> cookRcpApiResponseList = entities.stream()
                .map(entity -> response(entity))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(entities.getTotalPages())
                .totalElements(entities.getTotalElements())
                .currentPage(entities.getNumber())
                .currentElements(entities.getNumberOfElements())
                .build();

        return Header.OK(cookRcpApiResponseList, pagination);
    }
}