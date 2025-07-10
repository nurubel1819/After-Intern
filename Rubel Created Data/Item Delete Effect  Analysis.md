# Issue: Need Item Delete API for testing.

We may delete the item and check if the item is still available in prescription and other related places where item shows

Analysis: Item(clinic_items) this table associated 2 more table.
@ManyToMany
item---supplier
@OneToMany
item---CompanyItemStock
This two relation are bidirectional.

## In tis two relation No use CascadeType.Delete. As a result if item deleted from item table it can't delete CompanyItemStock and supplier table.

## In this situation item_id is present another table as a foreign-key. This forieign-key create problem.

Error become : referential integrity constraint violation(Because item_id is present both table as foreign key)

## If i use CascadeType.Delete it remove inventory_supplier and inventory_company_item_stock information. But i don't want this.

##(ManyToMany) Manually delete data from join table Ex: delete from join_table where i.id = 2;
Then delete item data.Because this time item_id not present in join table

##(OneToMany) Here i can use no Relation. Create a column item_id in CompanyItemStock table. But here no use any Jpa relation.In this situation set item_id find from database and manually set it.

Note : Not found item in prescription entity.
