package me.zhengjie.modules.mnt.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.mnt.domain.Database;
import me.zhengjie.modules.mnt.service.DatabaseService;
import me.zhengjie.modules.mnt.service.dto.DatabaseQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Api(tags = "数据库管理")
@RestController
@RequestMapping("/api/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @Log("查询Database")
    @ApiOperation(value = "查询Database")
    @GetMapping
	@PreAuthorize("@el.check('database:list')")
    public ResponseEntity getDatabases(DatabaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(databaseService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Database")
    @ApiOperation(value = "新增Database")
    @PostMapping
	@PreAuthorize("@el.check('database:add')")
    public ResponseEntity create(@Validated @RequestBody Database resources){
        return new ResponseEntity(databaseService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Database")
    @ApiOperation(value = "修改Database")
    @PutMapping
	@PreAuthorize("@el.check('database:edit')")
    public ResponseEntity update(@Validated @RequestBody Database resources){
        databaseService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Database")
    @ApiOperation(value = "删除Database")
    @DeleteMapping(value = "/{id}")
	@PreAuthorize("@el.check('database:del')")
    public ResponseEntity delete(@PathVariable String id){
        databaseService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
