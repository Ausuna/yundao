<head>
  <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
  <script src="http://how2j.cn/study/vue.min.js"></script>
  <style type="text/css">
    td{
      border:1px solid gray;
    }

    table{
      border-collapse:collapse;
    }

    div#app{
      margin:20px auto;
      width:400px;
      padding:20px;
    }
  </style>
</head>

<template>
  <div id="app">
    <el-table style="width: 100%"
              :data="heros"
              v-loading.body="tableLoading"
              element-loading-text="加载中"
              border fit highlight-current-row>
      <el-table-column prop="depart" label="学院"></el-table-column>
      <el-table-column prop="cla" label="班级"></el-table-column>
      <el-table-column prop="num" label="缺勤率"></el-table-column>
      <el-table-column prop="stu" label="缺勤名单"></el-table-column>
      <el-table-column prop="tim" label="点名时间"></el-table-column>


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-tooltip content="编辑" placement="top">
            <el-button @click="handleUpdate(scope.$index,scope.row)" size="medium" type="info" icon="el-icon-edit" circle plain></el-button>
          </el-tooltip>
          <!--&lt;!&ndash;<el-tooltip content="修改角色" placement="top" v-if="!hasAdminRole(scope.row)">&ndash;&gt;-->
            <!--&lt;!&ndash;<el-button @click="handleUpdateUserRoles(scope.$index,scope.row)" size="medium" type="warning" icon="el-icon-star-off" circle plain></el-button>&ndash;&gt;-->
          <!--&lt;!&ndash;</el-tooltip>&ndash;&gt;-->
          <!--&lt;!&ndash;<el-tooltip content="删除" placement="top" v-if="!hasAdminRole(scope.row)">&ndash;&gt;-->
            <!--&lt;!&ndash;<el-button @click="handleDelete(scope.$index,scope.row)" size="medium" type="danger" icon="el-icon-delete" circle plain></el-button>&ndash;&gt;-->
          <!--&lt;!&ndash;</el-tooltip>&ndash;&gt;-->
          <!--<el-popover trigger="hover" placement="top" v-else style="display: inline-block;">-->
            <!--<el-alert type="warning" :closable="false" title="权限说明">-->
              <!--<div>为保证管理员在系统中的最高权限</div>-->
              <!--<div>不允许编辑管理员自身的角色</div>-->
              <!--<div>不允许删除管理员账号</div>-->
            <!--</el-alert>-->
            <!--<div slot="reference" >-->
              <!--<el-tag style="margin-left: 10px;" type="info">权限说明</el-tag>-->
            <!--</div>-->
          <!--</el-popover>-->
        </template>
      </el-table-column>
      <!--</el-table-column>-->
    </el-table>
    <div style="margin-bottom: 30px;"></div>
    <el-pagination>
      <!--@size-change="handleSizeChange"-->
      <!--@current-change="handleCurrentChange"-->
      :current-page="tablePage.current"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="tablePage.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tablePage.total">
    </el-pagination>
    <table id="heroListTable" >
      <tbody>
      <tr>


        <td colspan="3">

          学院名称:
          <input type="text"    v-model="hero4Add.depart" />
          <br>
          班级名称:
          <input type="text"    v-model="hero4Add.cla" />
          <br>
          缺勤比例:
          <input type="text"    v-model="hero4Add.num" />
          <br>
          缺勤名单:
          <input type="text"    v-model="hero4Add.stu" />
          <br>
          点名时间:
          <input type="text"    v-model="hero4Add.tim" />
          <br>
          <button type="button"  v-on:click="add">增加</button>
        </td>
      </tr>
      </tbody>
    </table>

    <!--修改信息：-->

    <!--<div id="div4Update">-->
      <!--<td colspan="3">-->
      <!--英雄名称:-->
      <!--<input type="text"    v-model="hero4Update.depart" />-->
      <!--<br>-->
      <!--英雄名称:-->
      <!--<input type="text"    v-model="hero4Update.cla" />-->
      <!--<br>-->
      <!--英雄名称:-->
      <!--<input type="text"    v-model="hero4Update.num" />-->
      <!--<br>-->
      <!--英雄名称:-->
      <!--<input type="text"    v-model="hero4Update.stu" />-->
      <!--<br>-->
      <!--血量：-->
      <!--<input type="number"    v-model="hero4Update.tim" />-->
      <!--&lt;!&ndash;<input type="hidden"    v-model="hero4Update.id" />&ndash;&gt;-->
      <!--<br>-->
      <!--<button type="button"  v-on:click="update">修改</button>-->
      <!--<button type="button"  v-on:click="cancel">取消</button>-->
      <!--</td>-->
    <!--</div>-->
  </div>
</template>

<script>
  export default {
    name: 'Menu4_1_b',
    data() {
      return {
        heros: [
          { depart: '数计学院', cla: '计算机01班', num: 0.02 ,stu: 'lisi,wanwu',tim: '2019.06.05'},
          { depart: '数计学院', cla: '计算机02班', num: 0.02 ,stu: 'lisi,wanwu',tim: '2019.06.05'},
          { depart: '数计学院', cla: '计算机03班', num: 0.02 ,stu: 'lisi,wanwu',tim: '2019.06.05'},
          { depart: '数计学院', cla: '计算机04班', num: 0.02 ,stu: 'lisi,wanwu',tim: '2019.06.05'},


        ],
        hero4Add: { depart: '', cla: '', num: '',stu: '',tim: ''},
        hero4Update: { depart: '', cla: '', num: '',stu: '',tim: ''}
      }
    },
    methods: {
      add: function (event) {
        if(this.hero4Add.depart.length==0)
          this.hero4Add.depart = "Hero#"+this.hero4Add.depart;
        //把对象加入到数组
        this.heros.push(this.hero4Add);
        //让 hero4Add 指向新的对象
        this.hero4Add = { depart: '', cla: '', num: '0',stu: '',tim: ''}
      },
      deleteHero: function (depart) {
        console.log("depart"+depart);
        for (var i=0;i<this.heros.length;i++){
          if (this.heros[i].depart == depart) {
            this.heros.splice(i, 1);
            break;
          }
        }
      },
      edit: function (hero) {
//        $("#heroListTable").hide();
//        $("#div4Update").show();
        this.hero4Update = hero;
      },
      update:function(){
        //因为v-model，已经同步修改了，所以只需要进行恢复显示就行了
        $("#heroListTable").show();
        $("#div4Update").hide();
      },
      cancel:function(){
        //其实就是恢复显示
        $("#heroListTable").show();
        $("#div4Update").hide();
      }
    }

  }
</script>

