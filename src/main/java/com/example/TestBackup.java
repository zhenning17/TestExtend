package com.example;

import com.util.DbUnitService;
import com.util.LogsInit;
import org.databene.benerator.anno.Database;
import org.databene.benerator.anno.Values;


//@Database( id = "db",environment = "fee4db.env.properties")

public class TestBackup extends LogsInit {

    public static void main(String[] args) throws Exception {
        DbUnitService backup = new DbUnitService();
//       // 备份单张表
        backup.backupTable( "t_user","t_user123111111" );
        // 备份多张表
//        backup.backupTables( "t_result,t_role,t_system,t_user","20200722");
        // 备份全部数据表
//        backup.backupAllTables( "Interfaces" );

        // 恢复数据库
//        backup.restoreDb( "t_user" );

    }


}
