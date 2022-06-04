import React, { useState, useEffect, memo, useCallback } from 'react';

import ToAddBlock from '../block/to_add/ToAddBlock';
import ToAddIdBlock from '../block/to_add_id/ToAddIdBlock';
import styles from './BlockList.module.css';

import { TO_ADD_BLOCK_TYPE, TO_ADD_ID_BLOCK_TYPE } from '../../constants/BlockConstants';

const BlockList = memo(({ blocks, setBlocks }) => {
  const addBlock = (targetBlock) => {
    let idx = blocks.indexOf(targetBlock);
    let newBlocks = [...blocks];
    let newObject = { ...newBlocks[idx] };
    newObject.type = TO_ADD_ID_BLOCK_TYPE;
    newBlocks[idx] = newObject;
    setBlocks(newBlocks);
  };
  const blockComponentList = blocks.map((block) => {
    if (block.type == TO_ADD_BLOCK_TYPE) {
      return <ToAddBlock block={block} addBlock={addBlock} />;
    } else if (block.type == TO_ADD_ID_BLOCK_TYPE) {
      return <ToAddIdBlock block={block} />;
    } else {
      return <ToAddBlock block={block} />;
    }
  });
  return <div className={styles.grid}>{blockComponentList}</div>;
});

export default BlockList;
