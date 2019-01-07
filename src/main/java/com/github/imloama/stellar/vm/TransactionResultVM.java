package com.github.imloama.stellar.vm;

import lombok.Data;
import org.stellar.sdk.xdr.TransactionResult;
import org.stellar.sdk.xdr.TransactionResultCode;

import java.util.List;

@Data
public class TransactionResultVM {

    public static final int SUCCESS = 0;
    public static final int FAILED= -1;
    public static final int TOO_EARLY= -2;
    public static final int TOO_LATE = -3;
    public static final int MISSING_OPERATION= -4;
    public static final int BAD_SEQ= -5;
    public static final int BAD_AUTH= -6;
    public static final int INSUFFICIENT_BALANCE= -7;
    public static final int NO_ACCOUNT= -8;
    public static final int INSUFFICIENT_FEE= -9;
    public static final int BAD_AUTH_EXTRA= -10;
    public static final int INTERNAL_ERROR= -11;

    private int code;
    private String name;
    private List<OperationResultVM> list;

    public static TransactionResultVM fromTransactionResult(TransactionResult result){
        TransactionResultVM vm = new TransactionResultVM();
        TransactionResultCode resultCode = result.getResult().getDiscriminant();
        vm.code = resultCode.getValue();
        String name = resultCode.name();
        vm.name = name.substring(2);
        vm.list = OperationResultVM.fromOperationResults(result.getResult().getResults());
        return vm;
    }

    public boolean isSuccess(){
        return this.code == 0;
    }

    public boolean isFailed(){
        return this.code < 0;
    }


}
