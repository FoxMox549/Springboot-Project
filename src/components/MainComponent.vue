<script>
    export default {
      name: "MainComponent",
      data() {
        const item = {
          date: '05/01/2020',
          name: 'ash',
          address: '123 not found Ave'
        };
        return {
          tableData: Array(10).fill(item)
        }
      }
    }
</script>

<template>
    <el-table :data="tableData">
        <el-table-column prop="date" label="Date" width="140">
        </el-table-column>
        <el-table-column prop="name" label="Name" width="120">
        </el-table-column>
        <el-table-column prop="address" label="Address">
        </el-table-column>
    </el-table>
</template>

<style scoped>

</style>