package com.github.imloama.stellar.vm;

import lombok.Data;
import org.stellar.sdk.xdr.*;
import shadow.com.google.common.collect.Lists;

import java.util.List;

@Data
public class OperationResultVM {

    public static final int CREATE_ACCOUNT = 0;
    public static final int PAYMENT= 1;
    public static final int PATH_PAYMENT= 2;
    public static final int MANAGE_OFFER= 3;
    public static final int CREATE_PASSIVE_OFFER=4;
    public static final int SET_OPTIONS= 5;
    public static final int CHANGE_TRUST= 6;
    public static final int ALLOW_TRUST= 7;
    public static final int ACCOUNT_MERGE= 8;
    public static final int INFLATION= 9;
    public static final int MANAGE_DATA= 10;
    public static final int BUMP_SEQUENCE= 11;

    /**
     * operation type
     */
    private int type;
    /**
     * operation result code
     * https://www.stellar.org/developers/guides/concepts/list-of-operations.html
     */
    private int code;
    /**
     * operation result name
     * https://www.stellar.org/developers/guides/concepts/list-of-operations.html
     */
    private String name;


    public static List<OperationResultVM> fromOperationResults(OperationResult[] results) {
        if(results == null || results.length == 0)return Lists.newArrayList();
        List<OperationResultVM> list = Lists.newArrayList();
        for(OperationResult result : results){
            OperationResultVM vm = new OperationResultVM();
            OperationResult.OperationResultTr tr = result.getTr();
            int type = tr.getDiscriminant().getValue();
            vm.type = type;

            switch(type){
                case CREATE_ACCOUNT:
                    CreateAccountResultCode car = tr.getCreateAccountResult().getDiscriminant();
                    vm.name = car.name();
                    vm.code = car.getValue();
                    break;
                case PAYMENT:
                    PaymentResultCode rcode = tr.getPaymentResult().getDiscriminant();
                    vm.name = rcode.name();
                    vm.code = rcode.getValue();
                    break;
                case PATH_PAYMENT:
                    PathPaymentResultCode ppcode = tr.getPathPaymentResult().getDiscriminant();
                    vm.name = ppcode.name();
                    vm.code = ppcode.getValue();
                    break;
                case MANAGE_OFFER:
                    ManageOfferResultCode mocode = tr.getManageOfferResult().getDiscriminant();
                    vm.name = mocode.name();
                    vm.code = mocode.getValue();
                    break;
                case CREATE_PASSIVE_OFFER:
                    ManageOfferResultCode cpo = tr.getCreatePassiveOfferResult().getDiscriminant();
                    vm.name = cpo.name();
                    vm.code = cpo.getValue();
                    break;
                case SET_OPTIONS:
                    SetOptionsResultCode soc = tr.getSetOptionsResult().getDiscriminant();
                    vm.name = soc.name();
                    vm.code = soc.getValue();
                    break;
                case CHANGE_TRUST:
                    ChangeTrustResultCode ctc = tr.getChangeTrustResult().getDiscriminant();
                    vm.name = ctc.name();
                    vm.code = ctc.getValue();
                    break;
                case ALLOW_TRUST:
                    AllowTrustResultCode atc = tr.getAllowTrustResult().getDiscriminant();
                    vm.name = atc.name();
                    vm.code = atc.getValue();
                    break;
                case ACCOUNT_MERGE:
                    AccountMergeResultCode amc = tr.getAccountMergeResult().getDiscriminant();
                    vm.name = amc.name();
                    vm.code = amc.getValue();
                    break;
                case INFLATION:
                    InflationResultCode ic = tr.getInflationResult().getDiscriminant();
                    vm.name = ic.name();
                    vm.code = ic.getValue();
                    break;
                case MANAGE_DATA:
                    ManageDataResultCode mdc = tr.getManageDataResult().getDiscriminant();
                    vm.name = mdc.name();
                    vm.code = mdc.getValue();
                    break;
                case BUMP_SEQUENCE:
                    BumpSequenceResultCode bsc = tr.getBumpSeqResult().getDiscriminant();
                    vm.name = bsc.name();
                    vm.code = bsc.getValue();
                    break;
                default:
                    vm.name = null;
            }
            list.add(vm);
        }
        return list;
    }
}
